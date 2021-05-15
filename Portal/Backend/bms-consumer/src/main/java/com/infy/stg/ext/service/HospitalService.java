package com.infy.stg.ext.service;

import com.infy.stg.service.dto.HospitalDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.infy.stg.domain.Hospital}.
 */
public interface HospitalService extends com.infy.stg.service.HospitalService {

    @Transactional(readOnly = true)
    Optional<HospitalDTO> findByHospitalId(String hospitalId);
}
