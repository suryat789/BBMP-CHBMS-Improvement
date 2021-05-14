package com.infy.stg.repository;

import com.infy.stg.domain.PatientAudit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PatientAudit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientAuditRepository extends JpaRepository<PatientAudit, Long> {
}
