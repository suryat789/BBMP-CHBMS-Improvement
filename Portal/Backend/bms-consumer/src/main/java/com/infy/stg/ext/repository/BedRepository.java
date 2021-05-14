package com.infy.stg.ext.repository;

import com.infy.stg.domain.Bed;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Bed entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BedRepository extends com.infy.stg.repository.BedRepository, JpaRepository<Bed, Long> {
    Optional<Bed> findByHospitalHospitalIdAndType(String hospitalId, String type);
}
