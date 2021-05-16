package org.bbmp.chbms.repository;

import org.bbmp.chbms.domain.BedAudit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BedAudit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BedAuditRepository extends JpaRepository<BedAudit, Long> {
}
