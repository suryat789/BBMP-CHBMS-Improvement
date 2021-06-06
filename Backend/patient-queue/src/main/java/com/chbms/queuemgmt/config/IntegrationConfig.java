package com.chbms.queuemgmt.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class IntegrationConfig {

    @Value("${chbms.realtime_availability.url}")
    String realtimeBedAllotmentUrl;

    @Value("${chbms.bed_blocking.url}")
    String bedBlockingUrl;


    @Value("${chbdm.realtime_availability.username}")
    String realtimeBedAvailabilityUsername;

    @Value("${chbmd.bed_blocking.username}")
    String bedBlockingUserName;

    @Value("${chbdm.realtime_availability.password}")
    String realtimeBedAvailabilityPassword;

    @Value("${chbmd.bed_blocking.password}")
    String bedBlockingPassword;

    @Value("${dashboard.url}")
    String dashboardURL;

}
