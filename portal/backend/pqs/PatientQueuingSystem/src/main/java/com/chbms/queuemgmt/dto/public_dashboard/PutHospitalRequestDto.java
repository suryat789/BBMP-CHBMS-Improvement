package com.chbms.queuemgmt.dto.public_dashboard;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PutHospitalRequestDto {
    private List<BedDetails> beds;
    private String hospitalId;
    private String hospitalName;
    private String hospitalPhoneNumber;
    private String hospitalStatus;
    private String hospitalType;
    private Integer pincode;
    private String zone;

    @Data
    @Builder
    public static class BedDetails {
        private String bedType;
        private Long blocked;
        private Long capacity;
        private Long occupied;
        private Long vacant;
    }
}
