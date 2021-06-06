package org.bbmp.chbms.services;

import org.bbmp.chbms.model.Patients;

public interface PatientService {
    Patients pushPatientToEventHub(Patients patients);
}
