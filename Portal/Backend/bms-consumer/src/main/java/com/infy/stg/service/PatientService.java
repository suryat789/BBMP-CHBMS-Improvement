package com.infy.stg.service;

import com.infy.stg.service.dto.PatientDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.infy.stg.domain.Patient}.
 */
public interface PatientService {

    /**
     * Save a patient.
     *
     * @param patientDTO the entity to save.
     * @return the persisted entity.
     */
    PatientDTO save(PatientDTO patientDTO);

    /**
     * Get all the patients.
     *
     * @return the list of entities.
     */
    List<PatientDTO> findAll();


    /**
     * Get the "id" patient.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PatientDTO> findOne(Long id);

    /**
     * Delete the "id" patient.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
