package com.chbms.queuemgmt.controller;

import com.chbms.queuemgmt.data.entity.Patient;
import com.chbms.queuemgmt.data.repository.PatientRepository;
import com.chbms.queuemgmt.dto.PatientDto;
import com.chbms.queuemgmt.dto.patient.QueueStatusVO;
import com.chbms.queuemgmt.dto.queue.RemoveAndAddRequestDto;
import com.chbms.queuemgmt.enums.QueueType;
import com.chbms.queuemgmt.exception.BadRequestBodyException;
import com.chbms.queuemgmt.exception.PatientNotFoundException;
import com.chbms.queuemgmt.service.patient.PatientService;
import com.chbms.queuemgmt.validators.RequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/queuing-system/patients")
@Slf4j
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    RequestValidator requestValidator;

    @PostMapping
    public ResponseEntity<Object> addOrUpdatePatient(@Valid @RequestBody PatientDto patientDto) throws IOException, PatientNotFoundException, BadRequestBodyException {

        requestValidator.validatePatientRequest(patientDto);

        if (patientRepository.findById(patientDto.getPatientId()).isPresent()) {

            log.info("Request to Update Patient [ id = {} ] received with [ bedType = {} ,  zoneId = {}]", patientDto.getPatientId(), patientDto.getBedType(), patientDto.getZone());

            patientService.updatePatient(patientDto.getPatientId(), patientDto);

            return ResponseEntity.accepted().build();
        } else {

            log.info("Request to Add Patient [ id = {} ] ", patientDto.getPatientId());

            Patient patient = patientService.addPatient(patientDto);

            URI patientUri = ServletUriComponentsBuilder.
                    fromCurrentRequest()
                    .path("/{patientId}")
                    .buildAndExpand(patient.getPatientId())
                    .toUri();

            return ResponseEntity.created(patientUri).build();
        }
    }


    @GetMapping(path = "/{patientId}/status")
    public ResponseEntity<List<QueueStatusVO>> getPatientStatus(@PathVariable("patientId") Long patientId) {

        log.info("Request to get patient QueueEntry status for Patient [ id = {} ]", patientId);

        //TODO Should we check if patient is available in the system.

        List<QueueStatusVO> queueStatus = patientService.getQueueStatus(patientId);

        return ResponseEntity.ok(queueStatus);
    }

    @PostMapping(path = "/{patientId}/enqueue")
    public ResponseEntity<Object> enqueuePatient(@Valid @NotNull @PathVariable("patientId") Long patientId, @RequestBody List<QueueType> queueTypes) throws PatientNotFoundException, IOException {

        log.info("Request to enqueue patient [ id = {} ] into queues [ {} ]", patientId, queueTypes);

        patientService.enqueuePatient(patientId, queueTypes);

        return ResponseEntity.accepted().build();
    }

    @PostMapping(path = "/{patientId}/enqueueAtFront")
    public ResponseEntity<Object> enqueuePatientAtFront(@PathVariable("patientId") Long patientId, @RequestBody List<QueueType> queueNames) throws PatientNotFoundException, IOException {

        log.info("Request to Enqueue Patient [ {} ] as the front of queues [ {} ] received", patientId, queueNames);

        patientService.enqueuePatientAtFront(patientId, queueNames);

        return ResponseEntity.accepted().build();

    }

    @PostMapping(path = "/{patientId}/removeAndAddToQueue")
    public ResponseEntity<Object> removeAndAddToQueue(@PathVariable("patientId") Long patientId, @RequestBody RemoveAndAddRequestDto requestVO) throws PatientNotFoundException, IOException {


        log.info("Request to enqueue patient [ id = {} ] into queues [ {} ]", patientId, requestVO);

        patientService.removeAndAddToQueue(patientId, requestVO.getRemoveFromQueue(), requestVO.getAddToQueue());

        return ResponseEntity.accepted().build();
    }

    @DeleteMapping(path = "/{patientId}")
    public ResponseEntity<Object> removePatientFromAllQueues(@PathVariable("patientId") Long patientId) throws IOException, PatientNotFoundException {

        log.info("Request to delete Patient [ id = {} ] from all queues", patientId);

        patientService.removeFromAllQueues(patientId);

        return ResponseEntity.accepted().build();
    }

    @DeleteMapping(path = "/{patientId}/{queueName}")
    public ResponseEntity<Object> removePatientFromQueue(@PathVariable("patientId") Long patientId,
                                                         @PathVariable("queueName") QueueType queueType) throws IOException, PatientNotFoundException {

        log.info("Request to delete Patient [ id = {} ] from QueueEntry [ type = {} ]", patientId, queueType);

        patientService.removePatient(patientId, queueType);

        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/{patientId}")
    public ResponseEntity<Patient> getPatient(@PathVariable("patientId") Long patientId) throws PatientNotFoundException {

        log.info("Request to get Patient [ id = {} ]", patientId);

        Patient patient = patientService.getPatient(patientId);

        return ResponseEntity.ok(patient);
    }


}
