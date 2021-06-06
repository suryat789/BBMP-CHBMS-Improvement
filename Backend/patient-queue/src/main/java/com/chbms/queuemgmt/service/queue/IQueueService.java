package com.chbms.queuemgmt.service.queue;

import com.chbms.queuemgmt.enums.QueueType;
import com.chbms.queuemgmt.data.entity.QueueEntry;
import com.chbms.queuemgmt.dto.patient.QueueStatusVO;

import java.util.List;

public interface IQueueService {

    List<QueueStatusVO> getQueueStatus(Long patientId);

    List<QueueEntry> getQueueEntries(Long patientId);

    void removeEntry(Long patientId, QueueType queueType);

    QueueEntry enqueueEntry(Long patientId, String zone, QueueType requestedQueueType, boolean isFront);

    void removeEntries(Long patientId);

    List<QueueEntry> getQueuesByZone(List<QueueType> queueTypes, String zone, Integer pageNumber, Integer pageSize);

    List<QueueEntry> getQueues(List<QueueType> queueTypes, Integer pageNumber, Integer pageSize);
}
