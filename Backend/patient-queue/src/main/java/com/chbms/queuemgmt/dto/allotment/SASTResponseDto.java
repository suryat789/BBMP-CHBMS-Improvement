package com.chbms.queuemgmt.dto.allotment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class SASTResponseDto {

    @JsonProperty("HOSP_BED")
    List<HospitalDetailsVO> hospitalDetails;

}
