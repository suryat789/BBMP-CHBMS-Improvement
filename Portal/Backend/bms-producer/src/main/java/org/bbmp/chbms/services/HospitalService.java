package org.bbmp.chbms.services;

import org.bbmp.chbms.model.Hospitals;

public interface HospitalService {
    Hospitals pushHospitalToEventHub(Hospitals hospitals);
}
