package com.chbms.queuemgmt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class PatientDto {


    @JsonProperty("idcpatient")
    @NotNull(message = "patientId cannot be null")
    Long patientId;

    @JsonProperty("bucode")
    Long buNumber;

    @NotBlank
    @NotNull(message = "Contact number cannot be null or empty")
    @JsonProperty("pcnumber")
    @Size(min = 2, message = "Should be atleast of size 2")
    String contactNumber;

    @JsonProperty("icmrNumber")
    String icmrNumber;

    @NotBlank(message = "SRF cannot be blank")
    @JsonProperty("srfNumber")
    String srfNumber;

    @NotBlank(message = "Zone cannot be blank")
    @JsonProperty("pzone")
    String zone;

    @JsonProperty("pbtype")
    String bedType;

}
