package org.bbmp.chbms.services.mapper;


import org.bbmp.chbms.model.Hospitals;
import org.bbmp.chbms.services.dto.HospitalsMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HospitalMessageMapper {
    HospitalsMessage hospitalToHospitalMessage(Hospitals hospitals);
    Hospitals hospitalMessageFromHospitals(HospitalsMessage hospitalsMessage);
}
