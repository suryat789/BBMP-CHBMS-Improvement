package org.bbmp.chbms.ext.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.bbmp.chbms.service.PatientAuditService;
import org.bbmp.chbms.service.dto.PatientAuditDTO;
import org.bbmp.chbms.service.mapper.PatientAuditMapper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.bbmp.chbms.ext.service.PatientService;
import org.bbmp.chbms.domain.Patient;
import org.bbmp.chbms.ext.repository.PatientRepository;
import org.bbmp.chbms.service.dto.PatientDTO;
import org.bbmp.chbms.service.mapper.PatientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Patient}.
 */
@Primary
@Service("PatientService")
@Transactional
public class PatientServiceImpl extends org.bbmp.chbms.service.impl.PatientServiceImpl implements PatientService {

    private final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final PatientAuditService patientAuditService;
    private final PatientAuditMapper patientAuditMapper;

    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper, PatientAuditService patientAuditService, PatientAuditMapper patientAuditMapper) {
        super(patientRepository, patientMapper);
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.patientAuditService = patientAuditService;
        this.patientAuditMapper = patientAuditMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PatientDTO> findByPatientId(String patientId) {
        log.debug("Request to get Patient : {}", patientId);
        return patientRepository.findByPatientId(patientId)
            .map(patientMapper::toDto);
    }

    @Bean
    public synchronized Consumer<Message<Map>> consumePatient() {
        return message -> {
            log.info("Patient Event Received'{}'", message);
            PatientDTO patientDTO = mapToDTO(message.getPayload());
            try {
                patientDTO = save(patientDTO);
                PatientAuditDTO patientAuditDTO = mapToAuditDTO(patientDTO);
                patientAuditService.save(patientAuditDTO);
            } catch (DataIntegrityViolationException ex) {
                log.error(ex.getMessage(), ex);
            }
        };
    }

    private PatientDTO mapToDTO(Map<String, Object> payload) {
        Map<String, Object> map = new HashMap<String, Object>(payload);
        map.put("phone", map.remove("phoneLastFour"));
        map.remove("patientStatus");

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        PatientDTO patientDTO = mapper.convertValue(map, PatientDTO.class);

        List<String> nullFields = ((Map<String, Object>) mapper.convertValue(patientDTO, Map.class)).entrySet().stream()
            .filter(entry -> entry.getValue() == null)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        Optional<PatientDTO> patientOpt = findByPatientId(patientDTO.getPatientId());
        if (patientOpt.isPresent()) {
            PatientDTO patient = patientOpt.get();
            patientDTO.setId(patient.getId());
            BeanUtils.copyProperties(patientDTO, patient, nullFields.toArray(new String[0]));
            patientDTO = patient;
        } else {
            patientDTO.setCreatedOn(Instant.now());
        }
        patientDTO.setUpdatedOn(Instant.now());
        return patientDTO;
    }

    private PatientAuditDTO mapToAuditDTO(PatientDTO patientDTO) {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        PatientAuditDTO patientAuditDTO = mapper.convertValue(patientDTO, PatientAuditDTO.class);
        patientAuditDTO.setId(null);
        return patientAuditDTO;
    }
}
