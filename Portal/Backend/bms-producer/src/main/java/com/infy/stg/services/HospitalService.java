package com.infy.stg.services;

import com.infy.stg.model.Hospitals;

public interface HospitalService {
    Hospitals pushHospitalToEventHub(Hospitals hospitals);
}
