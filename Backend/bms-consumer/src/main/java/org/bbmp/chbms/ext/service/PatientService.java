package org.bbmp.chbms.ext.service;

import org.bbmp.chbms.service.dto.PatientDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link org.bbmp.chbms.domain.Patient}.
 */
public interface PatientService extends org.bbmp.chbms.service.PatientService {

    @Transactional(readOnly = true)
    Optional<PatientDTO> findByPatientId(String patientId);
}
