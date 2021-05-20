package com.chbms.queuemgmt.schedule.integrations;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CHBMSBedType {
    HDU("HDU"),
    ICU("ICU"),
    ICU_VENTILATOR("ICU-V");

    private String name;

    public String getVal() {
        return this.name;
    }
}
