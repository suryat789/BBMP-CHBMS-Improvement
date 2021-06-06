package com.chbms.queuemgmt.controller;

import com.chbms.queuemgmt.data.entity.QueueEntry;
import com.chbms.queuemgmt.enums.QueueType;
import com.chbms.queuemgmt.service.queue.IQueueService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/queuing-system/queues")
public class QueueController {

    @Autowired
    IQueueService queueService;

    @GetMapping
    public ResponseEntity<List<QueueEntry>> getQueue(
            @RequestParam(value = "queue") List<QueueType> queueTypes,
            @RequestParam(value = "zone", required = false) String zone,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "50") Integer pageSize) {

        if (StringUtils.isNotBlank(zone)) {
            List<QueueEntry> queueEntriesForZone = queueService.getQueuesByZone(queueTypes, zone, page, pageSize);
            return ResponseEntity.ok(queueEntriesForZone);
        } else {
            List<QueueEntry> queueEntries = queueService.getQueues(queueTypes, page, pageSize);
            return ResponseEntity.ok(queueEntries);
        }
    }

}
