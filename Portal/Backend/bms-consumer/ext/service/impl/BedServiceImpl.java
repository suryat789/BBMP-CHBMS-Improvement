package org.bbmp.chbms.ext.service.impl;

import org.bbmp.chbms.service.dto.HospitalDTO;
import org.springframework.context.annotation.Primary;
import org.bbmp.chbms.ext.service.BedService;
import org.bbmp.chbms.domain.Bed;
import org.bbmp.chbms.ext.repository.BedRepository;
import org.bbmp.chbms.service.dto.BedDTO;
import org.bbmp.chbms.service.mapper.BedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Bed}.
 */
@Primary
@Service("BedService")
@Transactional
public class BedServiceImpl extends org.bbmp.chbms.service.impl.BedServiceImpl implements BedService {

    private final Logger log = LoggerFactory.getLogger(BedServiceImpl.class);

    private final BedRepository bedRepository;

    private final BedMapper bedMapper;

    public BedServiceImpl(BedRepository bedRepository, BedMapper bedMapper) {
        super(bedRepository, bedMapper);
        this.bedRepository = bedRepository;
        this.bedMapper = bedMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BedDTO> findByHospitalHospitalIdAndType(String hospitalId, String type) {
        log.debug("Request to get Bed for Hispital/Type : {}/{}", hospitalId, type);
        return bedRepository.findByHospitalHospitalIdAndType(hospitalId, type)
            .map(bedMapper::toDto);
    }
}
