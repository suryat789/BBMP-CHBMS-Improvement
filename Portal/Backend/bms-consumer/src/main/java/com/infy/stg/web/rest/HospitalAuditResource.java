package com.infy.stg.web.rest;

import com.infy.stg.service.HospitalAuditService;
import com.infy.stg.web.rest.errors.BadRequestAlertException;
import com.infy.stg.service.dto.HospitalAuditDTO;

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
 * REST controller for managing {@link com.infy.stg.domain.HospitalAudit}.
 */
@RestController
@RequestMapping("/api")
public class HospitalAuditResource {

    private final Logger log = LoggerFactory.getLogger(HospitalAuditResource.class);

    private static final String ENTITY_NAME = "hospitalAudit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HospitalAuditService hospitalAuditService;

    public HospitalAuditResource(HospitalAuditService hospitalAuditService) {
        this.hospitalAuditService = hospitalAuditService;
    }

    /**
     * {@code POST  /hospital-audits} : Create a new hospitalAudit.
     *
     * @param hospitalAuditDTO the hospitalAuditDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hospitalAuditDTO, or with status {@code 400 (Bad Request)} if the hospitalAudit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/hospital-audits")
    public ResponseEntity<HospitalAuditDTO> createHospitalAudit(@RequestBody HospitalAuditDTO hospitalAuditDTO) throws URISyntaxException {
        log.debug("REST request to save HospitalAudit : {}", hospitalAuditDTO);
        if (hospitalAuditDTO.getId() != null) {
            throw new BadRequestAlertException("A new hospitalAudit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HospitalAuditDTO result = hospitalAuditService.save(hospitalAuditDTO);
        return ResponseEntity.created(new URI("/api/hospital-audits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /hospital-audits} : Updates an existing hospitalAudit.
     *
     * @param hospitalAuditDTO the hospitalAuditDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hospitalAuditDTO,
     * or with status {@code 400 (Bad Request)} if the hospitalAuditDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hospitalAuditDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/hospital-audits")
    public ResponseEntity<HospitalAuditDTO> updateHospitalAudit(@RequestBody HospitalAuditDTO hospitalAuditDTO) throws URISyntaxException {
        log.debug("REST request to update HospitalAudit : {}", hospitalAuditDTO);
        if (hospitalAuditDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HospitalAuditDTO result = hospitalAuditService.save(hospitalAuditDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hospitalAuditDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /hospital-audits} : get all the hospitalAudits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hospitalAudits in body.
     */
    @GetMapping("/hospital-audits")
    public List<HospitalAuditDTO> getAllHospitalAudits() {
        log.debug("REST request to get all HospitalAudits");
        return hospitalAuditService.findAll();
    }

    /**
     * {@code GET  /hospital-audits/:id} : get the "id" hospitalAudit.
     *
     * @param id the id of the hospitalAuditDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hospitalAuditDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/hospital-audits/{id}")
    public ResponseEntity<HospitalAuditDTO> getHospitalAudit(@PathVariable Long id) {
        log.debug("REST request to get HospitalAudit : {}", id);
        Optional<HospitalAuditDTO> hospitalAuditDTO = hospitalAuditService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hospitalAuditDTO);
    }

    /**
     * {@code DELETE  /hospital-audits/:id} : delete the "id" hospitalAudit.
     *
     * @param id the id of the hospitalAuditDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/hospital-audits/{id}")
    public ResponseEntity<Void> deleteHospitalAudit(@PathVariable Long id) {
        log.debug("REST request to delete HospitalAudit : {}", id);
        hospitalAuditService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
