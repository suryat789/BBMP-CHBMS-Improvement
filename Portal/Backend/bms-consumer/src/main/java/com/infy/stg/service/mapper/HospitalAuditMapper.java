package com.infy.stg.service.mapper;


import com.infy.stg.domain.*;
import com.infy.stg.service.dto.HospitalAuditDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link HospitalAudit} and its DTO {@link HospitalAuditDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HospitalAuditMapper extends EntityMapper<HospitalAuditDTO, HospitalAudit> {



    default HospitalAudit fromId(Long id) {
        if (id == null) {
            return null;
        }
        HospitalAudit hospitalAudit = new HospitalAudit();
        hospitalAudit.setId(id);
        return hospitalAudit;
    }
}
