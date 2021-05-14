package com.infy.stg.ext.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.infy.stg.domain.Bed;
import com.infy.stg.ext.repository.BedRepository;
import com.infy.stg.service.BedService;
import com.infy.stg.service.dto.BedAuditDTO;
import com.infy.stg.service.dto.BedDTO;
import com.infy.stg.service.mapper.BedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import com.infy.stg.ext.service.HospitalService;
import com.infy.stg.domain.Hospital;
import com.infy.stg.ext.repository.HospitalRepository;
import com.infy.stg.service.dto.HospitalDTO;
import com.infy.stg.service.mapper.HospitalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public HospitalServiceImpl(HospitalRepository hospitalRepository, HospitalMapper hospitalMapper) {
        super(hospitalRepository, hospitalMapper);
        this.hospitalRepository = hospitalRepository;
        this.hospitalMapper = hospitalMapper;
    }

    @Autowired
    private BedRepository bedRepository;
    @Autowired
    private BedMapper bedMapper;
    @Autowired
    private BedService bedService;

    @Bean
    public Consumer<Message<Map>> consumeHospitalBed() {
        return message -> {
            log.info("Hospital Event Received '{}'", message);
            Map<String, Object> map = new HashMap<String, Object>(message.getPayload());
            map.put("address", map.remove("hospitalAddress"));
            map.put("name", map.remove("hospitalName"));
            map.put("phone", map.remove("hospitalPhoneNumber"));
            map.put("type", map.remove("hospitalType"));
            map.put("status", map.remove("hospitalStatus"));
            map.put("updatedByMsgId", map.remove("updatedByMessageId"));
            ObjectMapper mapper = new ObjectMapper();
            Map[] beds = mapper.convertValue(map.remove("beds"), Map[].class);
            mapper.registerModule(new JavaTimeModule());
            HospitalDTO hospitalDTO = mapper.convertValue(map, HospitalDTO.class);
            Optional<Hospital> hispitalOpt = hospitalRepository.findByHospitalId(hospitalDTO.getHospitalId());
            hispitalOpt.ifPresent(hospital -> hospitalDTO.setId(hospital.getId()));
            Long hospitalId = save(hospitalDTO).getId();

            Arrays.stream(beds).forEach(m -> {
                m.put("type", m.remove("bedType"));
                BedDTO bedDTO = mapper.convertValue(m, BedDTO.class);
                Optional<Bed> bedOpt = bedRepository.findByHospitalHospitalIdAndType(hospitalDTO.getHospitalId(), bedDTO.getType());
                bedOpt.ifPresent(bed -> bedDTO.setId(bed.getId()));
                bedDTO.setHospitalId(hospitalId);
                bedService.save(bedDTO);
            });
            log.info("Beds Event Received '{}'", Arrays.toString(beds));

        };
    }
/**
 HospitalBed{
 "generalAvailability": 0,
 "generalBlockedCapacity": 0,
 "generalCapacity": 0,
 "generalOccupancy": 0,
 "hduAvailibility": 0,
 "hduBlockedCapacity": 0,
 "hduCapacity": 0,
 "hduOccupancy": 0,
 "hospitalAddress": "string",
 "hospitalId": "string",
 "hospitalName": "string",
 "hospitalPhoneNumber": "string",
 "icuAvailability": 0,
 "icuBlockedCapicity": 0,
 "icuCapacity": 0,
 "icuOccupancy": 0,
 "icuVentilatorAvailibility": 0,
 "icuVentilatorBlockedCapacity": 0,
 "icuVentilatorCapacity": 0,
 "icuVentilatorOccupancy": 0,
 "pinCode": "string",
 "updatedByMessageId": "string",
 "updatedOn": "2021-05-12T08:02:07.134Z",
 "zone": "string"
 }
 **/
}
