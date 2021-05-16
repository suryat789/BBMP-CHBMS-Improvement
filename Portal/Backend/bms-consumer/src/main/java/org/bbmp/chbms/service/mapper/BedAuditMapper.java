package org.bbmp.chbms.service.mapper;


import org.bbmp.chbms.domain.*;
import org.bbmp.chbms.service.dto.BedAuditDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BedAudit} and its DTO {@link BedAuditDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BedAuditMapper extends EntityMapper<BedAuditDTO, BedAudit> {



    default BedAudit fromId(Long id) {
        if (id == null) {
            return null;
        }
        BedAudit bedAudit = new BedAudit();
        bedAudit.setId(id);
        return bedAudit;
    }
}
