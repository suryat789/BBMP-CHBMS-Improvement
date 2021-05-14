package com.infy.stg.service.mapper;


import com.infy.stg.domain.*;
import com.infy.stg.service.dto.PatientAuditDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PatientAudit} and its DTO {@link PatientAuditDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PatientAuditMapper extends EntityMapper<PatientAuditDTO, PatientAudit> {



    default PatientAudit fromId(Long id) {
        if (id == null) {
            return null;
        }
        PatientAudit patientAudit = new PatientAudit();
        patientAudit.setId(id);
        return patientAudit;
    }
}
