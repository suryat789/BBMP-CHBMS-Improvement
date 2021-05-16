package org.bbmp.chbms.service.impl;

import org.bbmp.chbms.service.HospitalService;
import org.bbmp.chbms.domain.Hospital;
import org.bbmp.chbms.repository.HospitalRepository;
import org.bbmp.chbms.service.dto.HospitalDTO;
import org.bbmp.chbms.service.mapper.HospitalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Hospital}.
 */
@Service
@Transactional
public class HospitalServiceImpl implements HospitalService {

    private final Logger log = LoggerFactory.getLogger(HospitalServiceImpl.class);

    private final HospitalRepository hospitalRepository;

    private final HospitalMapper hospitalMapper;

    public HospitalServiceImpl(HospitalRepository hospitalRepository, HospitalMapper hospitalMapper) {
        this.hospitalRepository = hospitalRepository;
        this.hospitalMapper = hospitalMapper;
    }

    @Override
    public HospitalDTO save(HospitalDTO hospitalDTO) {
        log.debug("Request to save Hospital : {}", hospitalDTO);
        Hospital hospital = hospitalMapper.toEntity(hospitalDTO);
        hospital = hospitalRepository.save(hospital);
        return hospitalMapper.toDto(hospital);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HospitalDTO> findAll() {
        log.debug("Request to get all Hospitals");
        return hospitalRepository.findAll().stream()
            .map(hospitalMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<HospitalDTO> findOne(Long id) {
        log.debug("Request to get Hospital : {}", id);
        return hospitalRepository.findById(id)
            .map(hospitalMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Hospital : {}", id);
        hospitalRepository.deleteById(id);
    }
}
