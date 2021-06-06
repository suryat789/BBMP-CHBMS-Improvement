package org.bbmp.chbms.service;

import org.bbmp.chbms.service.dto.HospitalAuditDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link org.bbmp.chbms.domain.HospitalAudit}.
 */
public interface HospitalAuditService {

    /**
     * Save a hospitalAudit.
     *
     * @param hospitalAuditDTO the entity to save.
     * @return the persisted entity.
     */
    HospitalAuditDTO save(HospitalAuditDTO hospitalAuditDTO);

    /**
     * Get all the hospitalAudits.
     *
     * @return the list of entities.
     */
    List<HospitalAuditDTO> findAll();


    /**
     * Get the "id" hospitalAudit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HospitalAuditDTO> findOne(Long id);

    /**
     * Delete the "id" hospitalAudit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
