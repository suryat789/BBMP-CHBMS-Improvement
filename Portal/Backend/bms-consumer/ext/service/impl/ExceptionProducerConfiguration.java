package org.bbmp.chbms.ext.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.function.Supplier;

@Configuration
public class ExceptionProducerConfiguration {

    private static final Logger log = LoggerFactory.getLogger(ExceptionProducerConfiguration.class);

    @Bean
    public EmitterProcessor<Message<Map>> exceptionEmitter() {
        return EmitterProcessor.create();
    }


    @Bean
    public Supplier<Flux<Message<Map>>> produceException(EmitterProcessor<Message<Map>> emitter) {
        return () -> Flux.from(emitter)
            .doOnNext(m -> log.info("Sending Exception Record to event bus"))
            .doOnError(t -> log.error("Error encountered", t));
    }
}
