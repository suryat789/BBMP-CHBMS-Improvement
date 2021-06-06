package org.bbmp.chbms.configuration;

import org.bbmp.chbms.services.dto.HospitalsMessage;
import org.bbmp.chbms.services.dto.PatientsMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class ProducerConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerConfiguration.class);

    @Bean
    public Sinks.Many<Message<PatientsMessage>> patientEmitter() {
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Sinks.Many<Message<HospitalsMessage>> hospitalEmitter() {
        return Sinks.many().unicast().onBackpressureBuffer();
    }


    @Bean
    public Supplier<Flux<Message<PatientsMessage>>> patientProducer(Sinks.Many<Message<PatientsMessage>> many) {
        return () -> many.asFlux()
                .doOnNext(m -> LOGGER.info("Sending Patient Record to event bus {}", m.getHeaders().getId()))
                .doOnError(t -> LOGGER.error("Error encountered", t));
    }


    @Bean
    public Supplier<Flux<Message<HospitalsMessage>>> hospitalProducer(Sinks.Many<Message<HospitalsMessage>> many) {
        return () -> many.asFlux()
                .doOnNext(m -> LOGGER.info("Sending Hospital Record to event bus {}", m.getHeaders().getId()))
                .doOnError(t -> LOGGER.error("Error encountered", t));
    }

}
