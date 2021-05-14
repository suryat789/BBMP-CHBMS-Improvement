package com.infy.stg.web.rest;

import com.infy.stg.service.BedAuditService;
import com.infy.stg.web.rest.errors.BadRequestAlertException;
import com.infy.stg.service.dto.BedAuditDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.infy.stg.domain.BedAudit}.
 */
@RestController
@RequestMapping("/api")
public class BedAuditResource {

    private final Logger log = LoggerFactory.getLogger(BedAuditResource.class);

    private static final String ENTITY_NAME = "bedAudit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BedAuditService bedAuditService;

    public BedAuditResource(BedAuditService bedAuditService) {
        this.bedAuditService = bedAuditService;
    }

    /**
     * {@code POST  /bed-audits} : Create a new bedAudit.
     *
     * @param bedAuditDTO the bedAuditDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bedAuditDTO, or with status {@code 400 (Bad Request)} if the bedAudit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bed-audits")
    public ResponseEntity<BedAuditDTO> createBedAudit(@RequestBody BedAuditDTO bedAuditDTO) throws URISyntaxException {
        log.debug("REST request to save BedAudit : {}", bedAuditDTO);
        if (bedAuditDTO.getId() != null) {
            throw new BadRequestAlertException("A new bedAudit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BedAuditDTO result = bedAuditService.save(bedAuditDTO);
        return ResponseEntity.created(new URI("/api/bed-audits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bed-audits} : Updates an existing bedAudit.
     *
     * @param bedAuditDTO the bedAuditDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bedAuditDTO,
     * or with status {@code 400 (Bad Request)} if the bedAuditDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bedAuditDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bed-audits")
    public ResponseEntity<BedAuditDTO> updateBedAudit(@RequestBody BedAuditDTO bedAuditDTO) throws URISyntaxException {
        log.debug("REST request to update BedAudit : {}", bedAuditDTO);
        if (bedAuditDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BedAuditDTO result = bedAuditService.save(bedAuditDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bedAuditDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bed-audits} : get all the bedAudits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bedAudits in body.
     */
    @GetMapping("/bed-audits")
    public List<BedAuditDTO> getAllBedAudits() {
        log.debug("REST request to get all BedAudits");
        return bedAuditService.findAll();
    }

    /**
     * {@code GET  /bed-audits/:id} : get the "id" bedAudit.
     *
     * @param id the id of the bedAuditDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bedAuditDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bed-audits/{id}")
    public ResponseEntity<BedAuditDTO> getBedAudit(@PathVariable Long id) {
        log.debug("REST request to get BedAudit : {}", id);
        Optional<BedAuditDTO> bedAuditDTO = bedAuditService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bedAuditDTO);
    }

    /**
     * {@code DELETE  /bed-audits/:id} : delete the "id" bedAudit.
     *
     * @param id the id of the bedAuditDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bed-audits/{id}")
    public ResponseEntity<Void> deleteBedAudit(@PathVariable Long id) {
        log.debug("REST request to delete BedAudit : {}", id);
        bedAuditService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
