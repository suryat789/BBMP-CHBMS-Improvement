package com.chbms.queuemgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QueueType {

    GENERAL("GENERAL"),
    HDU("HDU"),
    ICU("ICU"),
    ICU_VENTILATOR("ICU_VENTILATOR"),

    ER_HDU("ER_HDU"),
    ER_ICU("ER_ICU"),
    ER_ICU_VENTILATOR("ER_ICU_VENTILATOR"),

    ER_REQUEST("ER_REQUEST"),

    PENDING_TRIAGING("PENDING_TRIAGING");

    private String name;

}
