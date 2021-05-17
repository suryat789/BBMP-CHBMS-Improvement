package org.bbmp.chbms.services.impl;

import org.bbmp.chbms.model.Patients;
import org.bbmp.chbms.services.PatientService;
import org.bbmp.chbms.services.dto.PatientsMessage;
import org.bbmp.chbms.services.mapper.PatientMessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.time.Instant;

@Service
public class PatientServiceImpl implements PatientService {

    private final Sinks.Many<Message<PatientsMessage>>  processor;

    private static final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);

    private final PatientMessageMapper patientMessageMapper;

    public PatientServiceImpl(Sinks.Many<Message<PatientsMessage>> processor, PatientMessageMapper patientMessageMapper) {
        this.processor = processor;
        this.patientMessageMapper = patientMessageMapper;
    }

    @Override
    public Patients pushPatientToEventHub(Patients patients) {
        log.debug("pushing Patient To event HUB {}", patients);
        PatientsMessage patientsMessage = patientMessageMapper.patientsToPatientsMessage(patients);
        patientsMessage.setUpdatedOn(Instant.now());
        patientsMessage.setUpdatedByMsgId("service");
        log.info("patients {} {}",patientsMessage.getClass().toString(), patientsMessage);
        processor.emitNext(MessageBuilder.withPayload(patientsMessage).build(), Sinks.EmitFailureHandler.FAIL_FAST);
        return patients;
    }
}
