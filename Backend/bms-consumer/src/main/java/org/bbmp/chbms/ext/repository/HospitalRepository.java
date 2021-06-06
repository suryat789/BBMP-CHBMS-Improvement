package org.bbmp.chbms.ext.repository;

import org.bbmp.chbms.domain.Hospital;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Hospital entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HospitalRepository extends org.bbmp.chbms.repository.HospitalRepository, JpaRepository<Hospital, Long> {

    Optional<Hospital> findByHospitalId(String hospitalId);
}
