package com.infy.stg.ext.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import com.infy.stg.ext.service.PatientService;
import com.infy.stg.domain.Patient;
import com.infy.stg.ext.repository.PatientRepository;
import com.infy.stg.service.dto.PatientDTO;
import com.infy.stg.service.mapper.PatientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Consumer;

/**
 * Service Implementation for managing {@link Patient}.
 */
@Primary
@Service("PatientService")
@Transactional
public class PatientServiceImpl extends com.infy.stg.service.impl.PatientServiceImpl implements PatientService {

    private final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);

    private final PatientRepository patientRepository;

    private final PatientMapper patientMapper;

    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper) {
        super(patientRepository, patientMapper);
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PatientDTO> findByPatientId(String patientId) {
        log.debug("Request to get Patient : {}", patientId);
        return patientRepository.findByPatientId(patientId)
            .map(patientMapper::toDto);
    }

    @Bean
    public Consumer<Message<Map>> consumePatient() {
        return message -> {
            log.info("Patient Event Received'{}'", message);
            Map<String, Object> map = new HashMap<String,Object>(message.getPayload());
            map.put("phone", map.remove("phoneLastFour"));
            map.remove("patientStatus");
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            PatientDTO patientDTO = mapper.convertValue(map, PatientDTO.class);
            Optional<Patient> patientOpt = patientRepository.findByPatientId(patientDTO.getPatientId());
            patientOpt.ifPresent(patient -> patientDTO.setId(patient.getId()));
            save(patientDTO);
        };
    }

//    Patient {
//        patientId: 4
//        phoneLastFour: 0
//        srfNumber: string
//        bucode: 1
//        category: string
//        zone: string
//        queueType: string
//        queueName: string
//        timeAddedToQueue: 2021-05-12T05:50:35.913Z
//        updatedOn: 2021-05-12T05:50:35.913Z
//        updatedByMsgId: string
//    }
}
