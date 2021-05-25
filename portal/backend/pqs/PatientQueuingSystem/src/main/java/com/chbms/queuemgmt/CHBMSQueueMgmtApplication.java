package com.chbms.queuemgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
//@EnableScheduling
public class CHBMSQueueMgmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(CHBMSQueueMgmtApplication.class, args);
    }

}
