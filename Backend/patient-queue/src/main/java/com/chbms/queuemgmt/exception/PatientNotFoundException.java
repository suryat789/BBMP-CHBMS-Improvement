package com.chbms.queuemgmt.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatientNotFoundException extends Exception {
    //TODO Please add more exception. Please go through this https://www.baeldung.com/exception-handling-for-rest-with-spring
    Long patientId;

}
