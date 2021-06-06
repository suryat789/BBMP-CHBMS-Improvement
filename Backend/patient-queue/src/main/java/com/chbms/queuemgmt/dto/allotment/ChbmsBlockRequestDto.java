package com.chbms.queuemgmt.dto.allotment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChbmsBlockRequestDto {

    Long patientId;
    String hospitalId;
    String bedType;

}
