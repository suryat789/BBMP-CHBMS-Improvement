package com.chbms.queuemgmt.validators;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BadFieldVO {

    String field;
    Object value;
    String issue;

}
