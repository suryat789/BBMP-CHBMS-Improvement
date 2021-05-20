package com.chbms.queuemgmt.validators;

import com.chbms.queuemgmt.dto.PatientDto;
import com.chbms.queuemgmt.exception.BadRequestBodyException;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class RequestValidator {

    public static final String validCharRegex = "^[a-zA-Z0-9+_\\-\\s]+$";

    public void validatePatientRequest(PatientDto patientDto) throws BadRequestBodyException {

        List<BadFieldVO> badFields = Lists.newArrayList();

        if (Objects.isNull(patientDto.getBuNumber())) {
            badFields.add(new BadFieldVO("bucode", patientDto.getBuNumber(), "Mandatory Field Missing"));
        }

        if (Objects.isNull(patientDto.getPatientId())) {
            badFields.add(new BadFieldVO("idcpatient", patientDto.getPatientId(), "Mandatory Field Missing"));
        }

        if (StringUtils.isBlank(patientDto.getZone()) || !patientDto.getZone().matches(validCharRegex)) {
            badFields.add(new BadFieldVO("pzone", patientDto.getZone(), "Mandatory Field Missing or Malformed"));
        }

        if (StringUtils.isBlank(patientDto.getContactNumber()) || !patientDto.getContactNumber().matches(validCharRegex)) {
            badFields.add(new BadFieldVO("pcnumber", patientDto.getContactNumber(), "Mandatory Field Missing or Malformed"));
        }

        if (StringUtils.isBlank(patientDto.getSrfNumber()) || !patientDto.getSrfNumber().matches(validCharRegex)) {
            badFields.add(new BadFieldVO("srfNumber", patientDto.getSrfNumber(), "Mandatory Field Missing or Malformed"));
        }

        if (StringUtils.isBlank(patientDto.getIcmrNumber()) || !patientDto.getIcmrNumber().matches(validCharRegex)) {
            badFields.add(new BadFieldVO("icmrNumber", patientDto.getIcmrNumber(), "Mandatory Field Missing or Malformed"));
        }

        if (CollectionUtils.isNotEmpty(badFields)) {
            throw new BadRequestBodyException(badFields);
        }
    }

}
