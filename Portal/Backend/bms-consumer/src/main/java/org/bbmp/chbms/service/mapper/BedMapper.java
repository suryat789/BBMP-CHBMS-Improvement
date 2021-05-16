package org.bbmp.chbms.service.mapper;


import org.bbmp.chbms.domain.*;
import org.bbmp.chbms.service.dto.BedDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Bed} and its DTO {@link BedDTO}.
 */
@Mapper(componentModel = "spring", uses = {HospitalMapper.class})
public interface BedMapper extends EntityMapper<BedDTO, Bed> {

    @Mapping(source = "hospital.id", target = "hospitalId")
    BedDTO toDto(Bed bed);

    @Mapping(source = "hospitalId", target = "hospital")
    Bed toEntity(BedDTO bedDTO);

    default Bed fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bed bed = new Bed();
        bed.setId(id);
        return bed;
    }
}
