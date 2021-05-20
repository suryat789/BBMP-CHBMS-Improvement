package org.bbmp.chbms.ext.web.rest;

import org.bbmp.chbms.ext.service.HospitalService;
import org.bbmp.chbms.web.rest.errors.BadRequestAlertException;
import org.bbmp.chbms.service.dto.HospitalDTO;

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
 * REST controller for managing {@link org.bbmp.chbms.domain.Hospital}.
 */

@RestController("HospitalResource")
@RequestMapping("/api")
public class HospitalResource extends org.bbmp.chbms.web.rest.HospitalResource {

    private final Logger log = LoggerFactory.getLogger(HospitalResource.class);

    private static final String ENTITY_NAME = "hospital";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HospitalService hospitalService;

    public HospitalResource(HospitalService hospitalService) {
        super(hospitalService);
        this.hospitalService = hospitalService;
    }
}
