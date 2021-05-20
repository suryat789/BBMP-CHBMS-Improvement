package com.chbms.queuemgmt.dto.allotment;

import com.chbms.queuemgmt.dto.public_dashboard.PutHospitalRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Arrays;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDetailsVO {
    // TODO: 2021-05-18 Also read data about total beds

    @JsonProperty("HOSP_CODE")
    String hospitalId;

    @JsonProperty("HOSP_ZONE")
    String zone;

    @JsonProperty("HOSP_NAME")
    String hospitalName;

    @JsonProperty("HOSP_CONTACT_NO")
    String hospitalContactNumber;

    @JsonProperty("HOSP_STATUS")
    String hospitalStatus;

    @JsonProperty("HOSP_TYPE")
    String hospitalType;


    @JsonProperty("GEN_VACCANT")
    Long generalBedVacant;

    @JsonProperty("HDU_VACCANT")
    Long hduBedVacant;

    @JsonProperty("ICU_VACCANT")
    Long icuBedVacant;

    @JsonProperty("ICUV_VACCANT")
    Long icuVentilatorVacant;


    @JsonProperty("GEN_BLOCKED")
    Long generalBedBlocked;

    @JsonProperty("HDU_BLOCKED")
    Long hduBedBlocked;

    @JsonProperty("ICU_BLOCKED")
    Long icuBedBlocked;

    @JsonProperty("ICUV_BLOCKED")
    Long icuVentilatorBlocked;


    @JsonProperty("GEN_CAPCITY")
    Long generalBedCapacity;

    @JsonProperty("HDU_CAPACITY")
    Long hduBedCapacity;

    @JsonProperty("ICU_CAPACITY")
    Long icuBedCapacity;

    @JsonProperty("ICUV_CAPACITY")
    Long icuVentilatorCapacity;


    @JsonProperty("GEN_OCCUPIED")
    Long generalBedOccupied;

    @JsonProperty("HDU_OCCUPIED")
    Long hduBedOccupied;

    @JsonProperty("ICU_OCCUPIED")
    Long icuBedOccupied;

    @JsonProperty("ICUV_OCCUPIED")
    Long icuVentilatorOccupied;


    public PutHospitalRequestDto putHospitalRequestDto() {
        return PutHospitalRequestDto
                .builder()
                .hospitalId(hospitalId)
                .hospitalName(hospitalName)
                .hospitalPhoneNumber(hospitalContactNumber)
                .hospitalStatus(hospitalStatus)
                .hospitalType(hospitalType)
                .zone(zone)
                .beds(Arrays.asList(
                        PutHospitalRequestDto.BedDetails
                                .builder()
                                .bedType("ICU")
                                .blocked(icuBedBlocked)
                                .capacity(icuBedCapacity)
                                .occupied(icuBedOccupied)
                                .vacant(icuBedVacant)
                                .build(),
                        PutHospitalRequestDto.BedDetails
                                .builder()
                                .bedType("HDU")
                                .blocked(hduBedBlocked)
                                .capacity(hduBedCapacity)
                                .occupied(hduBedOccupied)
                                .vacant(hduBedVacant)
                                .build(),
                        PutHospitalRequestDto.BedDetails
                                .builder()
                                .bedType("ICU_VENTILATOR")
                                .blocked(icuVentilatorBlocked)
                                .capacity(icuVentilatorCapacity)
                                .occupied(icuVentilatorOccupied)
                                .vacant(icuVentilatorVacant)
                                .build(),
                        PutHospitalRequestDto.BedDetails
                                .builder()
                                .bedType("GENERAL")
                                .blocked(generalBedBlocked)
                                .capacity(generalBedCapacity)
                                .occupied(generalBedOccupied)
                                .vacant(generalBedVacant)
                                .build()
                )).build();
    }
}
