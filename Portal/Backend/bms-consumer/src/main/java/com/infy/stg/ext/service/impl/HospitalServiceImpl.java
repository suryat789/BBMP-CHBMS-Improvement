package com.infy.stg.ext.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.infy.stg.ext.repository.BedRepository;
import com.infy.stg.ext.service.BedService;
import com.infy.stg.service.BedAuditService;
import com.infy.stg.service.HospitalAuditService;
import com.infy.stg.service.dto.*;
import com.infy.stg.service.mapper.BedMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import com.infy.stg.ext.service.HospitalService;
import com.infy.stg.domain.Hospital;
import com.infy.stg.ext.repository.HospitalRepository;
import com.infy.stg.service.mapper.HospitalMapper;
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
 * Service Implementation for managing {@link Hospital}.
 */
@Primary
@Service("HospitalService")
@Transactional
public class HospitalServiceImpl extends com.infy.stg.service.impl.HospitalServiceImpl implements HospitalService {

    private final Logger log = LoggerFactory.getLogger(HospitalServiceImpl.class);

    private final HospitalRepository hospitalRepository;

    private final HospitalMapper hospitalMapper;
    private HospitalAuditService hospitalAuditService;
    private BedAuditService bedAuditService;

    public HospitalServiceImpl(HospitalRepository hospitalRepository, HospitalMapper hospitalMapper, HospitalAuditService hospitalAuditService, BedAuditService bedAuditService) {
        super(hospitalRepository, hospitalMapper);
        this.hospitalRepository = hospitalRepository;
        this.hospitalMapper = hospitalMapper;
        this.hospitalAuditService = hospitalAuditService;
        this.bedAuditService = bedAuditService;
    }

    @Autowired
    private BedRepository bedRepository;
    @Autowired
    private BedMapper bedMapper;
    @Autowired
    private BedService bedService;

    @Override
    @Transactional(readOnly = true)
    public Optional<HospitalDTO> findByHospitalId(String hospitalId) {
        log.debug("Request to get Hospital : {}", hospitalId);
        return hospitalRepository.findByHospitalId(hospitalId)
            .map(hospitalMapper::toDto);
    }

    @Bean
    public synchronized Consumer<Message<Map>> consumeHospitalBed() {
        return message -> {
            log.info("Hospital Event Received '{}'", message);
            HospitalDTO hospitalDTO = mapToDTO(message.getPayload());
            try {
                hospitalDTO = save(hospitalDTO);
                HospitalAuditDTO hospitalAuditDTO = mapToAuditDTO(hospitalDTO);
                hospitalAuditService.save(hospitalAuditDTO);
                updateBeds(message.getPayload(), hospitalDTO);
            } catch (DataIntegrityViolationException ex) {
                log.error(ex.getMessage(), ex);
            }
        };
    }

    private HospitalDTO mapToDTO(Map<String, Object> payload) {
        Map<String, Object> map = new HashMap<String, Object>(payload);
        map.put("address", map.remove("hospitalAddress"));
        map.put("name", map.remove("hospitalName"));
        map.put("phone", map.remove("hospitalPhoneNumber"));
        map.put("type", map.remove("hospitalType"));
        map.put("status", map.remove("hospitalStatus"));
        map.put("updatedByMsgId", map.remove("updatedByMessageId"));

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        Map[] beds = mapper.convertValue(map.remove("beds"), Map[].class);

        HospitalDTO hospitalDTO = mapper.convertValue(map, HospitalDTO.class);

        List<String> nullFields = ((Map<String, Object>) mapper.convertValue(hospitalDTO, Map.class)).entrySet().stream()
            .filter(entry -> entry.getValue() == null)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        Optional<HospitalDTO> hospitalOpt = findByHospitalId(hospitalDTO.getHospitalId());
        if (hospitalOpt.isPresent()) {
            HospitalDTO hospital = hospitalOpt.get();
            hospitalDTO.setId(hospital.getId());
            BeanUtils.copyProperties(hospitalDTO, hospital, nullFields.toArray(new String[0]));
            hospitalDTO = hospital;
        } else {
            hospitalDTO.setCreatedOn(Instant.now());
        }
        hospitalDTO.setUpdatedOn(Instant.now());
        return hospitalDTO;
    }

    private HospitalAuditDTO mapToAuditDTO(HospitalDTO hospitalDTO) {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        HospitalAuditDTO hospitalAuditDTO = mapper.convertValue(hospitalDTO, HospitalAuditDTO.class);
        return hospitalAuditDTO;
    }

    private void updateBeds(Map<String, Object> payload, HospitalDTO hospitalDTO) {
        Map<String, Object> map = new HashMap<String, Object>(payload);
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        Map[] beds = mapper.convertValue(map.remove("beds"), Map[].class);

        Arrays.stream(beds).forEach(m -> {
            m.put("type", m.remove("bedType"));
            BedDTO bedDTO = mapper.convertValue(m, BedDTO.class);
            Optional<BedDTO> bedOpt = bedService.findByHospitalHospitalIdAndType(hospitalDTO.getHospitalId(), bedDTO.getType());

            List<String> nullFields = ((Map<String, Object>) mapper.convertValue(bedDTO, Map.class)).entrySet().stream()
                .filter(entry -> entry.getValue() == null)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

            if (bedOpt.isPresent()) {
                BedDTO bed = bedOpt.get();
                bedDTO.setId(bed.getId());
                BeanUtils.copyProperties(bedDTO, bed, nullFields.toArray(new String[0]));
                bedDTO = bed;
            } else {
                bedDTO.setCreatedOn(Instant.now());
            }
            bedDTO.setHospitalId(hospitalDTO.getId());
            bedDTO.setUpdatedOn(Instant.now());
            bedDTO.setUpdatedByMsgId(hospitalDTO.getUpdatedByMsgId());

            try{
                bedDTO = bedService.save(bedDTO);
                BedAuditDTO bedAuditDTO = mapToAuditDTO(bedDTO);
                bedAuditService.save(bedAuditDTO);
            }
            catch (DataIntegrityViolationException ex){
                log.error(ex.getMessage(), ex);
            }

        });
    }

    private BedAuditDTO mapToAuditDTO(BedDTO bedDTO) {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        BedAuditDTO bedAuditDTO = mapper.convertValue(bedDTO, BedAuditDTO.class);
        return bedAuditDTO;
    }
}
