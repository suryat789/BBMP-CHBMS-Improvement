package com.infy.stg.service.mapper;


import com.infy.stg.domain.*;
import com.infy.stg.service.dto.BedDTO;

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
