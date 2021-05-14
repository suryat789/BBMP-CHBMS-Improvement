package com.infy.stg.services.impl;

import com.infy.stg.model.Patients;
import com.infy.stg.services.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.time.Instant;

@Service
public class PatientServiceImpl implements PatientService {

    private final Sinks.Many<Message<Patients>>  processor;

    private static final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);

    public PatientServiceImpl(Sinks.Many<Message<Patients>> processor) {
        this.processor = processor;
    }

    @Override
    public Patients pushPatientToEventHub(Patients patients) {
        log.debug("pushing Patient To event HUB {}", patients);
        patients.setUpdatedOn(Instant.now());
        processor.emitNext(MessageBuilder.withPayload(patients).build(), Sinks.EmitFailureHandler.FAIL_FAST);
        return patients;
    }
}
