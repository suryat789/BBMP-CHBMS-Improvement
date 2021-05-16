package org.bbmp.chbms.ext.repository;

import org.bbmp.chbms.domain.Bed;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Bed entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BedRepository extends org.bbmp.chbms.repository.BedRepository, JpaRepository<Bed, Long> {
    Optional<Bed> findByHospitalHospitalIdAndType(String hospitalId, String type);
}
