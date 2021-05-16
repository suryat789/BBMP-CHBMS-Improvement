package org.bbmp.chbms.service.impl;

import org.bbmp.chbms.service.BedAuditService;
import org.bbmp.chbms.domain.BedAudit;
import org.bbmp.chbms.repository.BedAuditRepository;
import org.bbmp.chbms.service.dto.BedAuditDTO;
import org.bbmp.chbms.service.mapper.BedAuditMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link BedAudit}.
 */
@Service
@Transactional
public class BedAuditServiceImpl implements BedAuditService {

    private final Logger log = LoggerFactory.getLogger(BedAuditServiceImpl.class);

    private final BedAuditRepository bedAuditRepository;

    private final BedAuditMapper bedAuditMapper;

    public BedAuditServiceImpl(BedAuditRepository bedAuditRepository, BedAuditMapper bedAuditMapper) {
        this.bedAuditRepository = bedAuditRepository;
        this.bedAuditMapper = bedAuditMapper;
    }

    @Override
    public BedAuditDTO save(BedAuditDTO bedAuditDTO) {
        log.debug("Request to save BedAudit : {}", bedAuditDTO);
        BedAudit bedAudit = bedAuditMapper.toEntity(bedAuditDTO);
        bedAudit = bedAuditRepository.save(bedAudit);
        return bedAuditMapper.toDto(bedAudit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BedAuditDTO> findAll() {
        log.debug("Request to get all BedAudits");
        return bedAuditRepository.findAll().stream()
            .map(bedAuditMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BedAuditDTO> findOne(Long id) {
        log.debug("Request to get BedAudit : {}", id);
        return bedAuditRepository.findById(id)
            .map(bedAuditMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BedAudit : {}", id);
        bedAuditRepository.deleteById(id);
    }
}
