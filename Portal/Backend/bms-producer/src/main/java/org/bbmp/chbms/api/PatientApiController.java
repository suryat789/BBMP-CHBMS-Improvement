package org.bbmp.chbms.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bbmp.chbms.model.Patients;
import org.bbmp.chbms.services.PatientService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-10T06:55:16.648Z[GMT]")
@RestController
public class PatientApiController implements PatientApi {

    private static final Logger log = LoggerFactory.getLogger(PatientApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final PatientService patientService;

    @org.springframework.beans.factory.annotation.Autowired
    public PatientApiController(ObjectMapper objectMapper, HttpServletRequest request, PatientService patientService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.patientService = patientService;
    }



    public ResponseEntity<Patients> patientPost(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody Patients body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            log.info("sending patient to event hub ");
            Patients patient = patientService.pushPatientToEventHub(body);
            return new ResponseEntity<Patients>(patient, HttpStatus.CREATED);
        }

        return new ResponseEntity<Patients>(HttpStatus.NOT_IMPLEMENTED);
    }

}
