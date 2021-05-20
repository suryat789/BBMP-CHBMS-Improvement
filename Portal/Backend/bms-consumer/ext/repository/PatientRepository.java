package org.bbmp.chbms.ext.repository;

import org.bbmp.chbms.domain.Patient;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Patient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientRepository extends org.bbmp.chbms.repository.PatientRepository, JpaRepository<Patient, Long> {
    Optional<Patient> findByPatientId(String patientId);
}
