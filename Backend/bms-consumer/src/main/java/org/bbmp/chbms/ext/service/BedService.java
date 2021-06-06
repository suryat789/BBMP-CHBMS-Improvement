package org.bbmp.chbms.ext.service;

import org.bbmp.chbms.service.dto.BedDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link org.bbmp.chbms.domain.Bed}.
 */
public interface BedService extends org.bbmp.chbms.service.BedService {

    @Transactional(readOnly = true)
    Optional<BedDTO> findByHospitalHospitalIdAndType(String hospitalId, String type);
}
