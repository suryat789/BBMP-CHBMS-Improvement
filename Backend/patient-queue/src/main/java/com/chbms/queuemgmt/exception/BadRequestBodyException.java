package com.chbms.queuemgmt.exception;

import com.chbms.queuemgmt.validators.BadFieldVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class BadRequestBodyException extends Exception {

    List<BadFieldVO> badFields;

}
