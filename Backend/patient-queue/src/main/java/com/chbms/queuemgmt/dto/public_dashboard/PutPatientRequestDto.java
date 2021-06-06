package com.chbms.queuemgmt.dto.public_dashboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PutPatientRequestDto {
    private String patientId;
    private String phoneLastFour;
    private String srfNumber;
    private String bucode;
    private String zone;
    private String queueType;
    private String queueName;
    private String timeAddedToQueue;
}
