package com.chbms.queuemgmt.service.queue;

import com.chbms.queuemgmt.data.entity.QueueEntry;
import com.chbms.queuemgmt.data.repository.QueueRepository;
import com.chbms.queuemgmt.dto.patient.QueueStatusVO;
import com.chbms.queuemgmt.enums.QueueType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class QueueServiceImpl implements IQueueService {

    @Autowired
    QueueRepository queueRepository;

    /**
     * Gets the rank of the patients in the queue. First the pushed front patients are considered are considered for ranking. Ranking based on their enqueue time
     *
     * @param queueEntry
     * @return
     */
    private Long getPatientRank(QueueEntry queueEntry) {

        /**
         * Check only in push front queues
         */
        if (queueEntry.getPushFront() == -1) {
            return queueRepository.findRankByQueueTypePushed(queueEntry.getPatientId(), queueEntry.getType()) + 1;
        } else {
            Long frontPushedPatients = queueRepository.findPushedCountByQueueType(queueEntry.getType());
            Long rankAmongUnpushedPatients = queueRepository.findRankByQueueTypeUnPushed(queueEntry.getPatientId(), queueEntry.getType()) + 1;

            return frontPushedPatients + rankAmongUnpushedPatients;
        }
    }

    private QueueStatusVO map(QueueEntry queueEntry) {
        QueueStatusVO queueStatus = QueueStatusVO.builder()
                .queueType(QueueType.valueOf(queueEntry.getType()))
                .enqueueTimestamp(queueEntry.getEnqueueTimestamp())
                .build();

        queueStatus.setPatientRank(getPatientRank(queueEntry));

        return queueStatus;
    }

    @Override
    public List<QueueStatusVO> getQueueStatus(Long patientId) {
        List<QueueEntry> queueEntries = queueRepository.findByPatientIdAndIsActive(patientId, true);
        return queueEntries.stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public List<QueueEntry> getQueueEntries(Long patientId) {
        return queueRepository.findByPatientIdAndIsActive(patientId, true);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    @Modifying
    public void removeEntry(Long patientId, QueueType queueType) {
        Optional<QueueEntry> queueEntry = queueRepository.findByPatientIdAndIsActiveAndType(patientId, true, queueType.getName());
        if (queueEntry.isPresent()) {
            QueueEntry queue = queueEntry.get();
            queue.setIsActive(false);
            queueRepository.save(queue);
        } else {
            log.warn("Request To Delete QueueEntry Entry [ patientId = {}, queueType = {} ] which does not exist has arrived",
                    patientId, queueType);
        }
    }

    @Override
    @Transactional
    public QueueEntry enqueueEntry(Long patientId, String zone, QueueType requestedQueueType, boolean isFront) {

        QueueEntry queueEntry = QueueEntry
                .builder()
                .isActive(true)
                .type(requestedQueueType.getName())
                .patientId(patientId)
                .zone(zone)
                .enqueueTimestamp(Timestamp.from(Instant.now()))
                .pushFront(isFront ? -1 : 0)
                .build();

        QueueEntry savedQueueEntry = queueRepository.save(queueEntry);

        return savedQueueEntry;

    }

    @Override
    @Transactional
    public void removeEntries(Long patientId) {

        List<QueueEntry> queueEntries = queueRepository.findByPatientIdAndIsActive(patientId, true);

        for (QueueEntry queueEntry : queueEntries) {
            queueEntry.setIsActive(false);
        }

        queueRepository.saveAll(queueEntries);
    }

    @Override
    public List<QueueEntry> getQueuesByZone(List<QueueType> queueTypes, String zone, Integer pageNumber, Integer pageSize) {
        List<String> types = queueTypes.stream().map(QueueType::getName).collect(Collectors.toList());
        return queueRepository.getEntriesForTypesInAZone(types, zone, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public List<QueueEntry> getQueues(List<QueueType> queueTypes, Integer pageNumber, Integer pageSize) {
        List<String> types = queueTypes.stream().map(QueueType::getName).collect(Collectors.toList());
        return queueRepository.getEntriesForTypes(types, PageRequest.of(pageNumber, pageSize));
    }
}
