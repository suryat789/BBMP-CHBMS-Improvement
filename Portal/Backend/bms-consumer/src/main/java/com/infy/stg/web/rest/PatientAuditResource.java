package com.infy.stg.web.rest;

import com.infy.stg.service.PatientAuditService;
import com.infy.stg.web.rest.errors.BadRequestAlertException;
import com.infy.stg.service.dto.PatientAuditDTO;

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
 * REST controller for managing {@link com.infy.stg.domain.PatientAudit}.
 */
@RestController
@RequestMapping("/api")
public class PatientAuditResource {

    private final Logger log = LoggerFactory.getLogger(PatientAuditResource.class);

    private static final String ENTITY_NAME = "patientAudit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientAuditService patientAuditService;

    public PatientAuditResource(PatientAuditService patientAuditService) {
        this.patientAuditService = patientAuditService;
    }

    /**
     * {@code POST  /patient-audits} : Create a new patientAudit.
     *
     * @param patientAuditDTO the patientAuditDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientAuditDTO, or with status {@code 400 (Bad Request)} if the patientAudit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-audits")
    public ResponseEntity<PatientAuditDTO> createPatientAudit(@RequestBody PatientAuditDTO patientAuditDTO) throws URISyntaxException {
        log.debug("REST request to save PatientAudit : {}", patientAuditDTO);
        if (patientAuditDTO.getId() != null) {
            throw new BadRequestAlertException("A new patientAudit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PatientAuditDTO result = patientAuditService.save(patientAuditDTO);
        return ResponseEntity.created(new URI("/api/patient-audits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /patient-audits} : Updates an existing patientAudit.
     *
     * @param patientAuditDTO the patientAuditDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientAuditDTO,
     * or with status {@code 400 (Bad Request)} if the patientAuditDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientAuditDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-audits")
    public ResponseEntity<PatientAuditDTO> updatePatientAudit(@RequestBody PatientAuditDTO patientAuditDTO) throws URISyntaxException {
        log.debug("REST request to update PatientAudit : {}", patientAuditDTO);
        if (patientAuditDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PatientAuditDTO result = patientAuditService.save(patientAuditDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, patientAuditDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /patient-audits} : get all the patientAudits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientAudits in body.
     */
    @GetMapping("/patient-audits")
    public List<PatientAuditDTO> getAllPatientAudits() {
        log.debug("REST request to get all PatientAudits");
        return patientAuditService.findAll();
    }

    /**
     * {@code GET  /patient-audits/:id} : get the "id" patientAudit.
     *
     * @param id the id of the patientAuditDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientAuditDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-audits/{id}")
    public ResponseEntity<PatientAuditDTO> getPatientAudit(@PathVariable Long id) {
        log.debug("REST request to get PatientAudit : {}", id);
        Optional<PatientAuditDTO> patientAuditDTO = patientAuditService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patientAuditDTO);
    }

    /**
     * {@code DELETE  /patient-audits/:id} : delete the "id" patientAudit.
     *
     * @param id the id of the patientAuditDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-audits/{id}")
    public ResponseEntity<Void> deletePatientAudit(@PathVariable Long id) {
        log.debug("REST request to delete PatientAudit : {}", id);
        patientAuditService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
