package com.chbms.queuemgmt.dto.patient;

import com.chbms.queuemgmt.enums.QueueType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueueStatusVO {

    QueueType queueType;
    Long patientRank;
    Instant enqueueTimestamp;
}
