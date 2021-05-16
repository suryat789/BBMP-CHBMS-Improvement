package org.bbmp.chbms.services.mapper;

import org.bbmp.chbms.model.Patients;
import org.bbmp.chbms.services.dto.PatientsMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMessageMapper{
//    public PatientMessageMapper INSTANCE = Mappers.getMapper(PatientMessageMapper.class);
    PatientsMessage patientsToPatientsMessage(Patients patients);
    Patients patientsMessageToPatients(PatientsMessage patientsMessage);
}
