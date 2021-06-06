package com.chbms.queuemgmt.dto.queue;

import com.chbms.queuemgmt.enums.QueueType;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class RemoveAndAddRequestDto {
    List<QueueType> removeFromQueue;
    List<QueueType> addToQueue;
}
