package com.infy.stg.ext.repository;

import com.infy.stg.domain.Patient;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Patient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientRepository extends com.infy.stg.repository.PatientRepository, JpaRepository<Patient, Long> {
    Optional<Patient> findByPatientId(String patientId);
}
