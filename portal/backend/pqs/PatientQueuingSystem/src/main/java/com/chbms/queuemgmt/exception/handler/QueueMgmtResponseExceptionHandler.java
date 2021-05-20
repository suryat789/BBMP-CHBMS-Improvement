package com.chbms.queuemgmt.exception.handler;

import com.chbms.queuemgmt.exception.BadRequestBodyException;
import com.chbms.queuemgmt.validators.BadFieldVO;
import com.google.common.collect.Lists;
import com.chbms.queuemgmt.constants.ErrorConstants;
import com.chbms.queuemgmt.dto.error.DetailsVO;
import com.chbms.queuemgmt.dto.error.ErrorDto;
import com.chbms.queuemgmt.exception.PatientNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestController
@RestControllerAdvice
public class QueueMgmtResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private DetailsVO getErrorDetails(String location, String field, Object value, String issue) {
        DetailsVO detail = DetailsVO
                .builder()
                .location(location)
                .field(field)
                .value(value)
                .issue(issue)
                .build();

        return detail;
    }

    private ErrorDto getError(String name, String message) {
        ErrorDto errorDto = ErrorDto
                .builder()
                .name(name)
                .message(message)
                .build();

        return errorDto;
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorDto> handlePatientNotFound(PatientNotFoundException ex, WebRequest request) {

        DetailsVO detail = getErrorDetails(ErrorConstants.LOCATION_PATH, ErrorConstants.PATH_PARAM_PATIENT_ID, String.valueOf(ex.getPatientId()), "PATIENT_NOT_FOUND");

        ErrorDto errorDto = getError("PATIENT_NOT_FOUND", String.format("Patient [ patientId = [ %s ] ] not found", ex.getPatientId()));

        errorDto.setDetails(Lists.newArrayList(detail));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(BadRequestBodyException.class)
    public ResponseEntity<ErrorDto> handleBadPatientRequestException(BadRequestBodyException ex, WebRequest request) {

        List<DetailsVO> details  = Lists.newArrayList();

        for(BadFieldVO badField : ex.getBadFields()) {

            details.add(getErrorDetails(ErrorConstants.LOCATION_BODY, badField.getField(), badField.getValue(), badField.getIssue()));

        }

        ErrorDto errorDto = new ErrorDto("BAD_REQUEST", "Pateint Request Violated Some Constraints", details);

        return ResponseEntity.badRequest().body(errorDto);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if(ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;
            String argumentName = exception.getName();
            Object value = exception.getValue();
            Class<?> requiredFieldTypeClaass = exception.getRequiredType();
            String requiredType = requiredFieldTypeClaass.isEnum() ? StringUtils.join(requiredFieldTypeClaass.getEnumConstants(), ", ") : requiredFieldTypeClaass.getSimpleName();

            ErrorDto errorDto = getError("REQUEST_TYPE_NOT_VALID", String.format("[ %s ] is required to be of [ %s ], but given [ %s ]", argumentName, requiredType, value));
            DetailsVO detailsVO = getErrorDetails(ErrorConstants.LOCATION_PATH, argumentName, String.valueOf(value), String.format("Should be of [ %s ]", requiredType));

            errorDto.setDetails(Lists.newArrayList(detailsVO));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);

        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }


}
