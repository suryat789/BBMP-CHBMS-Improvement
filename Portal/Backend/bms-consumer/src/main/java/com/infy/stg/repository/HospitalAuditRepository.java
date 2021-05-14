package com.infy.stg.repository;

import com.infy.stg.domain.HospitalAudit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the HospitalAudit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HospitalAuditRepository extends JpaRepository<HospitalAudit, Long> {
}
