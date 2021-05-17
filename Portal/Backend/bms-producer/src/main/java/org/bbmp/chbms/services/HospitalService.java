package org.bbmp.chbms.services;

import org.bbmp.chbms.model.Hospitals;

import java.util.List;

public interface HospitalService {
    Hospitals pushHospitalToEventHub(Hospitals hospitals);
    List<Hospitals> pushHospitalsToEventHub (List<Hospitals> hospitalsList);
}
