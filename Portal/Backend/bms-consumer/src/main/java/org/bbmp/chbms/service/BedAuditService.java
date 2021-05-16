package org.bbmp.chbms.service;

import org.bbmp.chbms.service.dto.BedAuditDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link org.bbmp.chbms.domain.BedAudit}.
 */
public interface BedAuditService {

    /**
     * Save a bedAudit.
     *
     * @param bedAuditDTO the entity to save.
     * @return the persisted entity.
     */
    BedAuditDTO save(BedAuditDTO bedAuditDTO);

    /**
     * Get all the bedAudits.
     *
     * @return the list of entities.
     */
    List<BedAuditDTO> findAll();


    /**
     * Get the "id" bedAudit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BedAuditDTO> findOne(Long id);

    /**
     * Delete the "id" bedAudit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
