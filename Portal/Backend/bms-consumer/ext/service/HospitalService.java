package org.bbmp.chbms.ext.service;

import org.bbmp.chbms.service.dto.HospitalDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link org.bbmp.chbms.domain.Hospital}.
 */
public interface HospitalService extends org.bbmp.chbms.service.HospitalService {

    @Transactional(readOnly = true)
    Optional<HospitalDTO> findByHospitalId(String hospitalId);
}
