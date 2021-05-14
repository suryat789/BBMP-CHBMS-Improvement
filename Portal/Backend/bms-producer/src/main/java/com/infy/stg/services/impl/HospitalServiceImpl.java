package com.infy.stg.services.impl;

import com.infy.stg.model.Hospitals;
import com.infy.stg.services.HospitalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.time.Instant;

@Service
public class HospitalServiceImpl implements HospitalService {

    private final Sinks.Many<Message<Hospitals>>  processor;

    private static final Logger log = LoggerFactory.getLogger(HospitalServiceImpl.class);

    public HospitalServiceImpl(Sinks.Many<Message<Hospitals>> processor) {
        this.processor = processor;
    }

    @Override
    public Hospitals pushHospitalToEventHub(Hospitals hospitals) {
        log.debug("Pushing Hospitals To eventhub {}", hospitals);
        hospitals.updatedOn(Instant.now());
        processor.emitNext(MessageBuilder.withPayload(hospitals).build(), Sinks.EmitFailureHandler.FAIL_FAST);
        return hospitals;
    }
}
