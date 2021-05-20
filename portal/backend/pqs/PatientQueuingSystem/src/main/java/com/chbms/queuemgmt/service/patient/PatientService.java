package com.chbms.queuemgmt.service.patient;

import com.chbms.queuemgmt.data.entity.Patient;
import com.chbms.queuemgmt.data.entity.QueueEntry;
import com.chbms.queuemgmt.data.repository.PatientRepository;
import com.chbms.queuemgmt.data.repository.QueueRepository;
import com.chbms.queuemgmt.dto.PatientDto;
import com.chbms.queuemgmt.dto.patient.QueueStatusVO;
import com.chbms.queuemgmt.dto.public_dashboard.PutPatientRequestDto;
import com.chbms.queuemgmt.enums.QueueType;
import com.chbms.queuemgmt.exception.BadRequestBodyException;
import com.chbms.queuemgmt.exception.PatientNotFoundException;
import com.chbms.queuemgmt.schedule.integrations.PBIntegrator;
import com.chbms.queuemgmt.service.queue.IQueueService;
import com.chbms.queuemgmt.validators.BadFieldVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DozerBeanMapper beanMapper;

    @Autowired
    IQueueService queueService;


    @Autowired
    QueueRepository queueRepository;

    @Autowired
    PBIntegrator pbIntegrator;

    /**
     * Put the patient into a patient table and return patient Id
     *
     * @param patientVO - input patient
     */
    @Transactional
    public Patient addPatient(PatientDto patientVO) throws PatientNotFoundException {
        Patient patient = beanMapper.map(patientVO, Patient.class);

        patientRepository.save(patient);

        QueueEntry queueEntry = queueService.enqueueEntry(patient.getPatientId(), patient.getZone(), QueueType.PENDING_TRIAGING, false);

        queueRepository.save(queueEntry);

        pushToPublicDashboard(patient.getPatientId());

        return patient;
    }


    @Transactional
    public Patient updatePatient(Long patientId, PatientDto patientDto) throws PatientNotFoundException, BadRequestBodyException {
        Patient patient = patientRepository.findById(patientId).get();

        List<QueueEntry> queueEntries = queueService.getQueueEntries(patientId);

        if (patientDto.getBuNumber() != null && patientDto.getBuNumber() != 0) {
            if (!patientDto.getBuNumber().equals(patient.getBuNumber())) {
                throw new BadRequestBodyException(
                        Collections.singletonList(new BadFieldVO("bucode", patientDto.getBuNumber(),
                                "patient BU code is not matching with previous BU code for the patient. " +
                                        patient.getBuNumber()))
                );
            }
        }

        if (StringUtils.isNotBlank(patientDto.getContactNumber())) {
            patient.setContactNumber(patientDto.getContactNumber());
        }

        if (StringUtils.isNotBlank(patientDto.getIcmrNumber())) {
            patient.setIcmrNumber(patientDto.getIcmrNumber());
        }

        if (StringUtils.isNotBlank(patientDto.getZone())) {
            queueEntries
                    .forEach(queueEntry -> queueEntry.setZone(patientDto.getZone()));
            queueRepository.saveAll(queueEntries);
            patient.setZone(patientDto.getZone());
        }

        if (StringUtils.isNotBlank(patientDto.getSrfNumber())) {
            patient.setSrfNumber(patientDto.getSrfNumber());
        }

        Patient updatedPatient = patientRepository.save(patient);
        pushToPublicDashboard(patientId);

        return updatedPatient;
    }

    public Patient getPatient(Long patientId) throws PatientNotFoundException {
        return patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException(patientId));
    }

    public List<QueueStatusVO> getQueueStatus(Long patientId) {
        return queueService.getQueueStatus(patientId);
    }

    @Transactional
    public void enqueuePatient(Long patientId, List<QueueType> queueTypes) throws PatientNotFoundException {

        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException(patientId));
        List<String> existingQueues = queueService.getQueueEntries(patientId).stream()
                .map(QueueEntry::getType)
                .collect(Collectors.toList());

        for (QueueType queueType : queueTypes) {

            if (!existingQueues.contains(queueType.getName())) {
                queueService.enqueueEntry(patientId, patient.getZone(), queueType, false);
            }
        }

        pushToPublicDashboard(patientId);
    }

    @Transactional
    public void enqueuePatientAtFront(Long patientId, List<QueueType> queueTypes) throws PatientNotFoundException {

        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException(patientId));

        List<QueueEntry> queueEntries = queueService.getQueueEntries(patientId);

        List<String> existingNonPushed = queueEntries.stream()
                .filter(queueEntry -> queueEntry.getPushFront() == 0)
                .map(QueueEntry::getType)
                .collect(Collectors.toList());

        List<String> existingPushedQueues = queueEntries.stream()
                .filter(queueEntry -> queueEntry.getPushFront() == -1)
                .map(QueueEntry::getType)
                .collect(Collectors.toList());

        for (QueueType queueType : queueTypes) {
            if (existingNonPushed.contains(queueType.getName())) {
                queueService.removeEntry(patientId, queueType);
                queueService.enqueueEntry(patientId, patient.getZone(), queueType, true);
            } else if (!existingPushedQueues.contains(queueType.getName())) {
                queueService.enqueueEntry(patientId, patient.getZone(), queueType, true);
            }

        }

        pushToPublicDashboard(patientId);
    }

    @Transactional
    public void removeAndAddToQueue(Long patientId, List<QueueType> removeFrom, List<QueueType> addTo) throws PatientNotFoundException {

        for (QueueType queueType : removeFrom) {
            removePatient(patientId, queueType);
        }

        enqueuePatient(patientId, addTo);

        pushToPublicDashboard(patientId);
    }


    @Transactional
    public void removePatient(Long patientId, QueueType queueType) throws PatientNotFoundException {
        queueService.removeEntry(patientId, queueType);
        pushToPublicDashboard(patientId);
    }

    @Transactional
    public void removeFromAllQueues(Long patientId) throws PatientNotFoundException {
        queueService.removeEntries(patientId);
        pushToPublicDashboard(patientId);
    }

    private void pushToPublicDashboard(Long patientId) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException(patientId));
        List<QueueEntry> queues = queueService.getQueueEntries(patientId);
        boolean noEntries = true;
        for (QueueEntry queueEntry : queues) {
            if (!QueueType.ER_REQUEST.name().equals(queueEntry.getType())) {
                noEntries = false;
                pushDataToPublicDashboard(patient, queueEntry);
            }
        }

        if (noEntries) {
            pushNoneQueue(patient);
        }
    }

    private void pushDataToPublicDashboard(Patient patient, @Nullable QueueEntry queueEntry) {
        String contactNumber = patient.getContactNumber();
        String phoneLastFour = (contactNumber != null && contactNumber.length() >= 4) ? contactNumber.substring(contactNumber.length() - 4) : null;

        pbIntegrator.putPatient(PutPatientRequestDto.builder()
                .patientId(String.valueOf(patient.getPatientId()))
                .bucode(String.valueOf(patient.getBuNumber()))
                .srfNumber(patient.getSrfNumber())
                .phoneLastFour(phoneLastFour)
                .queueType((queueEntry != null) ? queueEntry.getType() : null)
                .queueName((queueEntry != null) ? queueEntry.getType() : null)
                .zone(patient.getZone())
                .timeAddedToQueue((queueEntry != null) ? queueEntry.getEnqueueTimestamp().toInstant().toString() : null)
                .build()
        );
    }

    private void pushNoneQueue(Patient patient) {
        String contactNumber = patient.getContactNumber();
        String phoneLastFour = (contactNumber != null && contactNumber.length() >= 4) ? contactNumber.substring(contactNumber.length() - 4) : null;

        pbIntegrator.putPatient(PutPatientRequestDto.builder()
                .patientId(String.valueOf(patient.getPatientId()))
                .bucode(String.valueOf(patient.getBuNumber()))
                .srfNumber(patient.getSrfNumber())
                .phoneLastFour(phoneLastFour)
                .queueType("None")
                .queueName("None")
                .zone(patient.getZone())
                .build()
        );
    }
}
