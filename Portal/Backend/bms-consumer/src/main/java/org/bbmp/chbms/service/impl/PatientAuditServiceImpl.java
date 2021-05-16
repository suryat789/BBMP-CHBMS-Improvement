package org.bbmp.chbms.service.impl;

import org.bbmp.chbms.service.PatientAuditService;
import org.bbmp.chbms.domain.PatientAudit;
import org.bbmp.chbms.repository.PatientAuditRepository;
import org.bbmp.chbms.service.dto.PatientAuditDTO;
import org.bbmp.chbms.service.mapper.PatientAuditMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PatientAudit}.
 */
@Service
@Transactional
public class PatientAuditServiceImpl implements PatientAuditService {

    private final Logger log = LoggerFactory.getLogger(PatientAuditServiceImpl.class);

    private final PatientAuditRepository patientAuditRepository;

    private final PatientAuditMapper patientAuditMapper;

    public PatientAuditServiceImpl(PatientAuditRepository patientAuditRepository, PatientAuditMapper patientAuditMapper) {
        this.patientAuditRepository = patientAuditRepository;
        this.patientAuditMapper = patientAuditMapper;
    }

    @Override
    public PatientAuditDTO save(PatientAuditDTO patientAuditDTO) {
        log.debug("Request to save PatientAudit : {}", patientAuditDTO);
        PatientAudit patientAudit = patientAuditMapper.toEntity(patientAuditDTO);
        patientAudit = patientAuditRepository.save(patientAudit);
        return patientAuditMapper.toDto(patientAudit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientAuditDTO> findAll() {
        log.debug("Request to get all PatientAudits");
        return patientAuditRepository.findAll().stream()
            .map(patientAuditMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PatientAuditDTO> findOne(Long id) {
        log.debug("Request to get PatientAudit : {}", id);
        return patientAuditRepository.findById(id)
            .map(patientAuditMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PatientAudit : {}", id);
        patientAuditRepository.deleteById(id);
    }
}
