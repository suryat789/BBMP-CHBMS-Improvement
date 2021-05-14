package com.infy.stg.services;

import com.infy.stg.model.Patients;

public interface PatientService {
    Patients pushPatientToEventHub(Patients patients);
}
