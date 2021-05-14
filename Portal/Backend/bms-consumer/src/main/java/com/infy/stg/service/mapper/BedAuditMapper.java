package com.infy.stg.service.mapper;


import com.infy.stg.domain.*;
import com.infy.stg.service.dto.BedAuditDTO;

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
