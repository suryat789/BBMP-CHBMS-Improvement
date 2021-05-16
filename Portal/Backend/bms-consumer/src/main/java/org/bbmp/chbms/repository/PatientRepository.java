package org.bbmp.chbms.repository;

import org.bbmp.chbms.domain.Patient;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Patient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
