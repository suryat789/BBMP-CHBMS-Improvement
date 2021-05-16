package org.bbmp.chbms.repository;

import org.bbmp.chbms.domain.HospitalAudit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the HospitalAudit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HospitalAuditRepository extends JpaRepository<HospitalAudit, Long> {
}
