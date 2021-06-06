package org.bbmp.chbms.service.mapper;


import org.bbmp.chbms.domain.*;
import org.bbmp.chbms.service.dto.HospitalAuditDTO;

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
