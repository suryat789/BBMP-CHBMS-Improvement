package org.bbmp.chbms.service.impl;

import org.bbmp.chbms.service.HospitalAuditService;
import org.bbmp.chbms.domain.HospitalAudit;
import org.bbmp.chbms.repository.HospitalAuditRepository;
import org.bbmp.chbms.service.dto.HospitalAuditDTO;
import org.bbmp.chbms.service.mapper.HospitalAuditMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link HospitalAudit}.
 */
@Service
@Transactional
public class HospitalAuditServiceImpl implements HospitalAuditService {

    private final Logger log = LoggerFactory.getLogger(HospitalAuditServiceImpl.class);

    private final HospitalAuditRepository hospitalAuditRepository;

    private final HospitalAuditMapper hospitalAuditMapper;

    public HospitalAuditServiceImpl(HospitalAuditRepository hospitalAuditRepository, HospitalAuditMapper hospitalAuditMapper) {
        this.hospitalAuditRepository = hospitalAuditRepository;
        this.hospitalAuditMapper = hospitalAuditMapper;
    }

    @Override
    public HospitalAuditDTO save(HospitalAuditDTO hospitalAuditDTO) {
        log.debug("Request to save HospitalAudit : {}", hospitalAuditDTO);
        HospitalAudit hospitalAudit = hospitalAuditMapper.toEntity(hospitalAuditDTO);
        hospitalAudit = hospitalAuditRepository.save(hospitalAudit);
        return hospitalAuditMapper.toDto(hospitalAudit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HospitalAuditDTO> findAll() {
        log.debug("Request to get all HospitalAudits");
        return hospitalAuditRepository.findAll().stream()
            .map(hospitalAuditMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<HospitalAuditDTO> findOne(Long id) {
        log.debug("Request to get HospitalAudit : {}", id);
        return hospitalAuditRepository.findById(id)
            .map(hospitalAuditMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete HospitalAudit : {}", id);
        hospitalAuditRepository.deleteById(id);
    }
}
