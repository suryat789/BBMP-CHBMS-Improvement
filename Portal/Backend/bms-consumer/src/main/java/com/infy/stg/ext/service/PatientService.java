package com.infy.stg.ext.service;

import com.infy.stg.service.dto.PatientDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.infy.stg.domain.Patient}.
 */
public interface PatientService extends com.infy.stg.service.PatientService {

    @Transactional(readOnly = true)
    Optional<PatientDTO> findByPatientId(String patientId);
}
