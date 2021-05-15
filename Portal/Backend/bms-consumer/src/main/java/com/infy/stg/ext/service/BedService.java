package com.infy.stg.ext.service;

import com.infy.stg.service.dto.BedDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.infy.stg.domain.Bed}.
 */
public interface BedService extends com.infy.stg.service.BedService {

    @Transactional(readOnly = true)
    Optional<BedDTO> findByHospitalHospitalIdAndType(String hospitalId, String type);
}
