package com.infy.stg.service;

import com.infy.stg.service.dto.BedDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.infy.stg.domain.Bed}.
 */
public interface BedService {

    /**
     * Save a bed.
     *
     * @param bedDTO the entity to save.
     * @return the persisted entity.
     */
    BedDTO save(BedDTO bedDTO);

    /**
     * Get all the beds.
     *
     * @return the list of entities.
     */
    List<BedDTO> findAll();


    /**
     * Get the "id" bed.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BedDTO> findOne(Long id);

    /**
     * Delete the "id" bed.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
