package com.chbms.queuemgmt.schedule.integrations;

import com.chbms.queuemgmt.config.IntegrationConfig;
import com.chbms.queuemgmt.dto.allotment.HospitalDetailsVO;
import com.chbms.queuemgmt.dto.allotment.SASTResponseDto;
import com.chbms.queuemgmt.dto.public_dashboard.PutHospitalRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SASTIntegrator {

    @Autowired
    IntegrationConfig integrationConfig;

    @Autowired
    PBIntegrator pbIntegrator;

    @Autowired
    RestTemplate restTemplate;

    @Retryable(value = Exception.class, maxAttemptsExpression = "${retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
    public SASTResponseDto getRealtimeBedAvailability() throws JsonProcessingException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(integrationConfig.getRealtimeBedAvailabilityUsername(), integrationConfig.getRealtimeBedAvailabilityPassword());

        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(integrationConfig.getRealtimeBedAllotmentUrl(), HttpMethod.GET, httpEntity, String.class);

        String responseString = response.getBody();

        SASTResponseDto sastResponseDto = new ObjectMapper().readValue(responseString, SASTResponseDto.class);
        log.info("SAST Response {}", sastResponseDto);

        // un-comment for testing
        sastResponseDto.setHospitalDetails(Arrays.asList(
                HospitalDetailsVO.builder()
                        .hospitalId("H_0055")
                        .zone("4")
                        .hospitalName("Test hospital Z 4 1")
                        .generalBedVacant(100L)
                        .hduBedVacant(1L)
                        .icuBedVacant(1L)
                        .icuVentilatorVacant(0L)
                        .build(),
                HospitalDetailsVO.builder()
                        .hospitalId("H_0056")
                        .zone("4")
                        .hospitalName("Test hospital Z 4 2")
                        .generalBedVacant(100L)
                        .hduBedVacant(1L)
                        .icuBedVacant(0L)
                        .icuVentilatorVacant(0L)
                        .build(),
                HospitalDetailsVO.builder()
                        .hospitalId("H_0057")
                        .zone("5")
                        .hospitalName("Test hospital Z 5 1")
                        .generalBedVacant(100L)
                        .hduBedVacant(1L)
                        .icuBedVacant(1L)
                        .icuVentilatorVacant(0L)
                        .build(),
                HospitalDetailsVO.builder()
                        .hospitalId("H_0058")
                        .zone("5")
                        .hospitalName("Test hospital Z 5 2")
                        .generalBedVacant(100L)
                        .hduBedVacant(1L)
                        .icuBedVacant(0L)
                        .icuVentilatorVacant(0L)
                        .build()
        ));
        List<PutHospitalRequestDto> hospitals = sastResponseDto
                .getHospitalDetails()
                .stream()
                .map(HospitalDetailsVO::putHospitalRequestDto)
                .collect(Collectors.toList());
        pbIntegrator.putHospitals(hospitals);
        return sastResponseDto;
    }

}
