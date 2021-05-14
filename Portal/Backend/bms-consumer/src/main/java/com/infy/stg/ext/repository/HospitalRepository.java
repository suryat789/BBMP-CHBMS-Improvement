package com.infy.stg.ext.repository;

import com.infy.stg.domain.Hospital;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Hospital entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HospitalRepository extends com.infy.stg.repository.HospitalRepository, JpaRepository<Hospital, Long> {

    Optional<Hospital> findByHospitalId(String hospitalId);
}
