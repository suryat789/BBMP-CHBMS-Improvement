package com.infy.stg.ext.service.impl;

import org.springframework.context.annotation.Primary;
import com.infy.stg.ext.service.BedService;
import com.infy.stg.domain.Bed;
import com.infy.stg.ext.repository.BedRepository;
import com.infy.stg.service.dto.BedDTO;
import com.infy.stg.service.mapper.BedMapper;
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
public class BedServiceImpl extends com.infy.stg.service.impl.BedServiceImpl implements BedService {

    private final Logger log = LoggerFactory.getLogger(BedServiceImpl.class);

    private final BedRepository bedRepository;

    private final BedMapper bedMapper;

    public BedServiceImpl(BedRepository bedRepository, BedMapper bedMapper) {
        super(bedRepository, bedMapper);
        this.bedRepository = bedRepository;
        this.bedMapper = bedMapper;
    }
}
