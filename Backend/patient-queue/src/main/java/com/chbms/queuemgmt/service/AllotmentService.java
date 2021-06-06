package com.chbms.queuemgmt.service;

import com.chbms.queuemgmt.data.entity.Allotment;
import com.chbms.queuemgmt.data.entity.QueueEntry;
import com.chbms.queuemgmt.data.repository.AllotmentRepository;
import com.chbms.queuemgmt.dto.allotment.HospitalDetailsVO;
import com.chbms.queuemgmt.enums.QueueType;
import com.chbms.queuemgmt.schedule.integrations.CHBMSIntegrator;
import com.chbms.queuemgmt.service.queue.IQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AllotmentService {

    @Autowired
    AllotmentRepository allotmentRepository;

    @Autowired
    CHBMSIntegrator chbmsIntegrator;

    @Autowired
    IQueueService queueService;

    private void reduceBedCountForHospital(QueueType queueType, HospitalDetailsVO hospitalDetail) {

        switch (queueType) {
            case ER_ICU_VENTILATOR:
                hospitalDetail.setIcuVentilatorVacant(hospitalDetail.getIcuVentilatorVacant() - 1);
                break;
            case ER_ICU:
                hospitalDetail.setIcuBedVacant(hospitalDetail.getIcuBedVacant() - 1);
                break;
            case ER_HDU:
                hospitalDetail.setHduBedVacant(hospitalDetail.getHduBedVacant() - 1);
                break;
            default:
                log.warn("Request to allot bed of type [ {} ] has been declared void as it is not a critical request", queueType);
                return;
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void allotPatientToHospitalBed(QueueType queueType, HospitalDetailsVO hospitalDetail, QueueEntry queueEntry) throws Exception {
        String hospitalId = hospitalDetail.getHospitalId();
        Long patientId = queueEntry.getPatientId();
        chbmsIntegrator.blockBed(patientId, hospitalId, queueType);
        queueService.removeEntries(patientId);
        auditAllotment(queueEntry, queueType, hospitalId);
        reduceBedCountForHospital(queueType, hospitalDetail);
        log.info("Patient [ id = {} ] successfully allotted Bed [ hospitalId = {}, type = {} ]", queueEntry.getPatientId(),
                hospitalDetail.getHospitalId(), queueType);
    }

    private void auditAllotment(QueueEntry queueEntry, QueueType queueType, String hospitalId) {
        Allotment allotment = Allotment
                .builder()
                .bedType(queueType.getName())
                .queueId(queueEntry.getId())
                .patientId(queueEntry.getPatientId())
                .hospitalId(hospitalId)
                .requestTime(queueEntry.getEnqueueTimestamp())
                .zone(queueEntry.getZone())
                .build();
        log.info("Saving allotment in DB {}", allotment);
        allotmentRepository.save(allotment);
    }

}
