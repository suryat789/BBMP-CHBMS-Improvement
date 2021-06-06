package com.chbms.queuemgmt.config;

import com.squareup.okhttp.OkHttpClient;
import org.dozer.DozerBeanMapper;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import javax.validation.Validator;


@Configuration
public class GeneralConfig {

    @Autowired
    IntegrationConfig integrationConfig;

    @Bean
    public DozerBeanMapper getMapper() {
        return new DozerBeanMapper();
    }

    @Bean
    @Primary
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "realTimeAvailability")
    public RestTemplate getRealTimeAvailabilityTemplate() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                .basicAuthentication(integrationConfig.getRealtimeBedAllotmentUrl(), integrationConfig.getRealtimeBedAvailabilityPassword());

        RestTemplate restTemplate = restTemplateBuilder.build();

        return restTemplate;
    }

    @Bean
    public OkHttpClient okHttpClient() {

        return new OkHttpClient();
    }

}
