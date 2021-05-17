package org.bbmp.chbms.services.impl;

import org.bbmp.chbms.model.Hospitals;
import org.bbmp.chbms.services.HospitalService;
import org.bbmp.chbms.services.dto.HospitalsMessage;
import org.bbmp.chbms.services.mapper.HospitalMessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalServiceImpl implements HospitalService {

    private final Sinks.Many<Message<HospitalsMessage>>  processor;

    private static final Logger log = LoggerFactory.getLogger(HospitalServiceImpl.class);

    private  final HospitalMessageMapper hospitalMessageMapper;

    public HospitalServiceImpl(Sinks.Many<Message<HospitalsMessage>> processor, HospitalMessageMapper hospitalMessageMapper) {
        this.processor = processor;
        this.hospitalMessageMapper = hospitalMessageMapper;
    }

    @Override
    public Hospitals pushHospitalToEventHub(Hospitals hospitals) {
        log.debug("Pushing Hospitals To eventhub {}", hospitals);
        HospitalsMessage hospitalsMessage = hospitalMessageMapper.hospitalToHospitalMessage(hospitals);
        hospitalsMessage.setUpdatedOn(Instant.now());
        hospitalsMessage.setUpdatedByMessageId("service");
        log.info("Sending hospital To event hub {}", hospitalsMessage);
        processor.emitNext(MessageBuilder.withPayload(hospitalsMessage).build(), Sinks.EmitFailureHandler.FAIL_FAST);
        return hospitals;
    }

    @Override
    public List<Hospitals> pushHospitalsToEventHub(List<Hospitals> hospitalsList) {
        log.debug("Pushing List of Hospitals to eventhub {}", hospitalsList);
        List<HospitalsMessage> hospitalsMessages = hospitalsList.stream()
                .map(hospitalMessageMapper::hospitalToHospitalMessage)
                .peek(hospitalsMessage -> {
                    hospitalsMessage.setUpdatedOn(Instant.now());
                    hospitalsMessage.setUpdatedByMessageId("service");
                    processor.emitNext(MessageBuilder.withPayload(hospitalsMessage).build(), Sinks.EmitFailureHandler.FAIL_FAST);
                })
                .collect(Collectors.toList());
        log.debug("hospital List {}", hospitalsMessages);
        return hospitalsList;
    }
}
