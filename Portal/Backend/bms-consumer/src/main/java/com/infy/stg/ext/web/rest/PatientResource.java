package com.infy.stg.ext.web.rest;

import com.infy.stg.ext.service.PatientService;
import com.infy.stg.web.rest.errors.BadRequestAlertException;
import com.infy.stg.service.dto.PatientDTO;

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
 * REST controller for managing {@link com.infy.stg.domain.Patient}.
 */

@RestController("PatientResource")
@RequestMapping("/api")
public class PatientResource extends com.infy.stg.web.rest.PatientResource {

    private final Logger log = LoggerFactory.getLogger(PatientResource.class);

    private static final String ENTITY_NAME = "patient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientService patientService;

    public PatientResource(PatientService patientService) {
        super(patientService);
        this.patientService = patientService;
    }
}
