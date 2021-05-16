package org.bbmp.chbms.repository;

import org.bbmp.chbms.domain.Bed;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bed entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BedRepository extends JpaRepository<Bed, Long> {
}
