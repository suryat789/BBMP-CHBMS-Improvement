package org.bbmp.chbms.service.impl;

import org.bbmp.chbms.service.BedService;
import org.bbmp.chbms.domain.Bed;
import org.bbmp.chbms.repository.BedRepository;
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
@Service
@Transactional
public class BedServiceImpl implements BedService {

    private final Logger log = LoggerFactory.getLogger(BedServiceImpl.class);

    private final BedRepository bedRepository;

    private final BedMapper bedMapper;

    public BedServiceImpl(BedRepository bedRepository, BedMapper bedMapper) {
        this.bedRepository = bedRepository;
        this.bedMapper = bedMapper;
    }

    @Override
    public BedDTO save(BedDTO bedDTO) {
        log.debug("Request to save Bed : {}", bedDTO);
        Bed bed = bedMapper.toEntity(bedDTO);
        bed = bedRepository.save(bed);
        return bedMapper.toDto(bed);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BedDTO> findAll() {
        log.debug("Request to get all Beds");
        return bedRepository.findAll().stream()
            .map(bedMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BedDTO> findOne(Long id) {
        log.debug("Request to get Bed : {}", id);
        return bedRepository.findById(id)
            .map(bedMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Bed : {}", id);
        bedRepository.deleteById(id);
    }
}
