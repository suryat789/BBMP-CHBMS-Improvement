package com.chbms.queuemgmt.schedule;

import com.chbms.queuemgmt.data.entity.QueueEntry;
import com.chbms.queuemgmt.dto.allotment.HospitalDetailsVO;
import com.chbms.queuemgmt.dto.allotment.SASTResponseDto;
import com.chbms.queuemgmt.enums.QueueType;
import com.chbms.queuemgmt.schedule.integrations.SASTIntegrator;
import com.chbms.queuemgmt.service.AllotmentService;
import com.chbms.queuemgmt.service.queue.IQueueService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.chbms.queuemgmt.enums.QueueType.*;

@Component
@Slf4j
public class AllotmentScheduler {

    @Autowired
    AllotmentService allotmentService;

    @Autowired
    SASTIntegrator sastIntegrator;

    @Autowired
    IQueueService queueService;

    private static final Gson gson = new Gson();

    private boolean allotPatient(QueueEntry queueEntry, List<HospitalDetailsVO> possibleHospitals) {

        for (HospitalDetailsVO hospital : possibleHospitals) {
            log.info("Trying to allocated bed for patient {} in hospital {}", queueEntry.getPatientId(), hospital);

            try {
                int icuVVacant = ObjectUtils.defaultIfNull(hospital.getIcuVentilatorVacant(), 0L).intValue();
                int icuVacant = ObjectUtils.defaultIfNull(hospital.getIcuBedVacant(), 0L).intValue();
                int hduVacant = ObjectUtils.defaultIfNull(hospital.getHduBedVacant(), 0L).intValue();

                if (StringUtils.equalsIgnoreCase(queueEntry.getType(), ER_ICU_VENTILATOR.getName()) && icuVVacant > 0) {

                    /*
                     * Allots patients. Removes him from other queues and removes the hospitals particular bed count;
                     */
                    allotmentService.allotPatientToHospitalBed(ER_ICU_VENTILATOR, hospital, queueEntry);

                    return true;

                } else if (StringUtils.equalsIgnoreCase(queueEntry.getType(), ER_ICU.getName()) && icuVacant > 0) {

                    allotmentService.allotPatientToHospitalBed(ER_ICU, hospital, queueEntry);

                    return true;

                } else if (StringUtils.equalsIgnoreCase(queueEntry.getType(), ER_HDU.getName()) && hduVacant > 0) {

                    allotmentService.allotPatientToHospitalBed(ER_HDU, hospital, queueEntry);

                    return true;
                } else if (!Lists.newArrayList(ER_ICU_VENTILATOR.getName(), ER_ICU.getName(), ER_HDU.getName()).contains(queueEntry.getType())) {

                    log.error("Error as Request to Allot Patient for Queue Type [ {} ] has been made and is declared void", queueEntry.getType());

                    return false;
                }
            } catch (Exception ex) {
                log.error("Exception Occurred while allotting bed for Patient [ id = {} ] to hospital [ id = {}, bedType = {}  ]",
                        queueEntry.getPatientId(), hospital.getHospitalId(), queueEntry.getType());
                log.error(ExceptionUtils.getStackTrace(ex));
            }

        }

        /*
         * No bed has beed found for the patient
         */

        log.info("Unable to Allot Patient [ id = {} ] for Queue Type [ {} ] ", queueEntry.getPatientId(), queueEntry.getType());

        return false;

    }

    private void allotPatientsInQueue(QueueType queueType, Map<String, List<HospitalDetailsVO>> hospitalsGroupedByZone, List<HospitalDetailsVO> allHospitals) {

        List<QueueEntry> queueEntries = queueService.getQueues(Lists.newArrayList(queueType), 0, 10000);

        log.info("Found {} patients waiting for bedType {}", queueEntries.size(), queueType.getName());


        for (QueueEntry patientEntry : queueEntries) {

            List<HospitalDetailsVO> hospitalsInZone = hospitalsGroupedByZone.get(patientEntry.getZone());

            boolean allotted = false;
            if (hospitalsInZone != null) {
                allotted = allotPatient(patientEntry, hospitalsInZone);
            }

            if (!allotted) {

                log.info("Unable to find a hospital in zone [ {} ] for patient [ id = {} ] requesting Bed [ type = {} ]",
                        patientEntry.getZone(), patientEntry.getPatientId(), queueType);

                /*
                 * Unable to find hospitals in the zone. So finding hospitals across zones
                 */
                boolean allHospitalsAllotted = allotPatient(patientEntry, allHospitals);

                if (!allHospitalsAllotted) {
                    log.info("Unable to find a hospital in any zone for patient [ id = {} ] requesting Bed [ type = {} ]",
                            patientEntry.getPatientId(), queueType);
                }

            }
            log.info("Hospitals {}", gson.toJson(allHospitals));
        }

    }

    @Scheduled(initialDelay = 0, fixedDelayString = "${allotment.schedule.fixedDelay}")
    public void allot() throws JsonProcessingException {
        log.info("Started cron for ER auto allocation");

        SASTResponseDto realtimeBedAvailability = sastIntegrator.getRealtimeBedAvailability();
        List<HospitalDetailsVO> hospitals = realtimeBedAvailability.getHospitalDetails();

        log.info("Hospitals found {}", hospitals.size());

        Map<String, List<HospitalDetailsVO>> hospitalsGroupedByZone = hospitals.stream()
                .filter(hospital -> StringUtils.isNotBlank(hospital.getZone()))
                .collect(Collectors.groupingBy(HospitalDetailsVO::getZone));

        allotPatientsInQueue(ER_ICU_VENTILATOR, hospitalsGroupedByZone, hospitals);
        allotPatientsInQueue(ER_ICU, hospitalsGroupedByZone, hospitals);
        allotPatientsInQueue(ER_HDU, hospitalsGroupedByZone, hospitals);
    }

}
