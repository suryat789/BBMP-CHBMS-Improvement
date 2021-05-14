package com.infy.stg.web.rest;

import com.infy.stg.BmsApp;
import com.infy.stg.domain.Patient;
import com.infy.stg.repository.PatientRepository;
import com.infy.stg.service.PatientService;
import com.infy.stg.service.dto.PatientDTO;
import com.infy.stg.service.mapper.PatientMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PatientResource} REST controller.
 */
@SpringBootTest(classes = BmsApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PatientResourceIT {

    private static final String DEFAULT_PATIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_SRF_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SRF_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_BUCODE = "AAAAAAAAAA";
    private static final String UPDATED_BUCODE = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_ZONE = "AAAAAAAAAA";
    private static final String UPDATED_ZONE = "BBBBBBBBBB";

    private static final String DEFAULT_QUEUE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_QUEUE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_QUEUE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_QUEUE_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_TIME_ADDED_TO_QUEUE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME_ADDED_TO_QUEUE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CREATED_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATED_BY_MSG_ID = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_MSG_ID = "BBBBBBBBBB";

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private PatientService patientService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPatientMockMvc;

    private Patient patient;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Patient createEntity(EntityManager em) {
        Patient patient = new Patient()
            .patientId(DEFAULT_PATIENT_ID)
            .phone(DEFAULT_PHONE)
            .srfNumber(DEFAULT_SRF_NUMBER)
            .bucode(DEFAULT_BUCODE)
            .category(DEFAULT_CATEGORY)
            .zone(DEFAULT_ZONE)
            .queueType(DEFAULT_QUEUE_TYPE)
            .queueName(DEFAULT_QUEUE_NAME)
            .timeAddedToQueue(DEFAULT_TIME_ADDED_TO_QUEUE)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedOn(DEFAULT_UPDATED_ON)
            .updatedByMsgId(DEFAULT_UPDATED_BY_MSG_ID);
        return patient;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Patient createUpdatedEntity(EntityManager em) {
        Patient patient = new Patient()
            .patientId(UPDATED_PATIENT_ID)
            .phone(UPDATED_PHONE)
            .srfNumber(UPDATED_SRF_NUMBER)
            .bucode(UPDATED_BUCODE)
            .category(UPDATED_CATEGORY)
            .zone(UPDATED_ZONE)
            .queueType(UPDATED_QUEUE_TYPE)
            .queueName(UPDATED_QUEUE_NAME)
            .timeAddedToQueue(UPDATED_TIME_ADDED_TO_QUEUE)
            .createdOn(UPDATED_CREATED_ON)
            .updatedOn(UPDATED_UPDATED_ON)
            .updatedByMsgId(UPDATED_UPDATED_BY_MSG_ID);
        return patient;
    }

    @BeforeEach
    public void initTest() {
        patient = createEntity(em);
    }

    @Test
    @Transactional
    public void createPatient() throws Exception {
        int databaseSizeBeforeCreate = patientRepository.findAll().size();
        // Create the Patient
        PatientDTO patientDTO = patientMapper.toDto(patient);
        restPatientMockMvc.perform(post("/api/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientDTO)))
            .andExpect(status().isCreated());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeCreate + 1);
        Patient testPatient = patientList.get(patientList.size() - 1);
        assertThat(testPatient.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testPatient.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testPatient.getSrfNumber()).isEqualTo(DEFAULT_SRF_NUMBER);
        assertThat(testPatient.getBucode()).isEqualTo(DEFAULT_BUCODE);
        assertThat(testPatient.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testPatient.getZone()).isEqualTo(DEFAULT_ZONE);
        assertThat(testPatient.getQueueType()).isEqualTo(DEFAULT_QUEUE_TYPE);
        assertThat(testPatient.getQueueName()).isEqualTo(DEFAULT_QUEUE_NAME);
        assertThat(testPatient.getTimeAddedToQueue()).isEqualTo(DEFAULT_TIME_ADDED_TO_QUEUE);
        assertThat(testPatient.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testPatient.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
        assertThat(testPatient.getUpdatedByMsgId()).isEqualTo(DEFAULT_UPDATED_BY_MSG_ID);
    }

    @Test
    @Transactional
    public void createPatientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = patientRepository.findAll().size();

        // Create the Patient with an existing ID
        patient.setId(1L);
        PatientDTO patientDTO = patientMapper.toDto(patient);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPatientMockMvc.perform(post("/api/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPatients() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList
        restPatientMockMvc.perform(get("/api/patients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(patient.getId().intValue())))
            .andExpect(jsonPath("$.[*].patientId").value(hasItem(DEFAULT_PATIENT_ID)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].srfNumber").value(hasItem(DEFAULT_SRF_NUMBER)))
            .andExpect(jsonPath("$.[*].bucode").value(hasItem(DEFAULT_BUCODE)))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY)))
            .andExpect(jsonPath("$.[*].zone").value(hasItem(DEFAULT_ZONE)))
            .andExpect(jsonPath("$.[*].queueType").value(hasItem(DEFAULT_QUEUE_TYPE)))
            .andExpect(jsonPath("$.[*].queueName").value(hasItem(DEFAULT_QUEUE_NAME)))
            .andExpect(jsonPath("$.[*].timeAddedToQueue").value(hasItem(DEFAULT_TIME_ADDED_TO_QUEUE.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedByMsgId").value(hasItem(DEFAULT_UPDATED_BY_MSG_ID)));
    }
    
    @Test
    @Transactional
    public void getPatient() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get the patient
        restPatientMockMvc.perform(get("/api/patients/{id}", patient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(patient.getId().intValue()))
            .andExpect(jsonPath("$.patientId").value(DEFAULT_PATIENT_ID))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.srfNumber").value(DEFAULT_SRF_NUMBER))
            .andExpect(jsonPath("$.bucode").value(DEFAULT_BUCODE))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY))
            .andExpect(jsonPath("$.zone").value(DEFAULT_ZONE))
            .andExpect(jsonPath("$.queueType").value(DEFAULT_QUEUE_TYPE))
            .andExpect(jsonPath("$.queueName").value(DEFAULT_QUEUE_NAME))
            .andExpect(jsonPath("$.timeAddedToQueue").value(DEFAULT_TIME_ADDED_TO_QUEUE.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()))
            .andExpect(jsonPath("$.updatedByMsgId").value(DEFAULT_UPDATED_BY_MSG_ID));
    }
    @Test
    @Transactional
    public void getNonExistingPatient() throws Exception {
        // Get the patient
        restPatientMockMvc.perform(get("/api/patients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePatient() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        int databaseSizeBeforeUpdate = patientRepository.findAll().size();

        // Update the patient
        Patient updatedPatient = patientRepository.findById(patient.getId()).get();
        // Disconnect from session so that the updates on updatedPatient are not directly saved in db
        em.detach(updatedPatient);
        updatedPatient
            .patientId(UPDATED_PATIENT_ID)
            .phone(UPDATED_PHONE)
            .srfNumber(UPDATED_SRF_NUMBER)
            .bucode(UPDATED_BUCODE)
            .category(UPDATED_CATEGORY)
            .zone(UPDATED_ZONE)
            .queueType(UPDATED_QUEUE_TYPE)
            .queueName(UPDATED_QUEUE_NAME)
            .timeAddedToQueue(UPDATED_TIME_ADDED_TO_QUEUE)
            .createdOn(UPDATED_CREATED_ON)
            .updatedOn(UPDATED_UPDATED_ON)
            .updatedByMsgId(UPDATED_UPDATED_BY_MSG_ID);
        PatientDTO patientDTO = patientMapper.toDto(updatedPatient);

        restPatientMockMvc.perform(put("/api/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientDTO)))
            .andExpect(status().isOk());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
        Patient testPatient = patientList.get(patientList.size() - 1);
        assertThat(testPatient.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatient.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testPatient.getSrfNumber()).isEqualTo(UPDATED_SRF_NUMBER);
        assertThat(testPatient.getBucode()).isEqualTo(UPDATED_BUCODE);
        assertThat(testPatient.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testPatient.getZone()).isEqualTo(UPDATED_ZONE);
        assertThat(testPatient.getQueueType()).isEqualTo(UPDATED_QUEUE_TYPE);
        assertThat(testPatient.getQueueName()).isEqualTo(UPDATED_QUEUE_NAME);
        assertThat(testPatient.getTimeAddedToQueue()).isEqualTo(UPDATED_TIME_ADDED_TO_QUEUE);
        assertThat(testPatient.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testPatient.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
        assertThat(testPatient.getUpdatedByMsgId()).isEqualTo(UPDATED_UPDATED_BY_MSG_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingPatient() throws Exception {
        int databaseSizeBeforeUpdate = patientRepository.findAll().size();

        // Create the Patient
        PatientDTO patientDTO = patientMapper.toDto(patient);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPatientMockMvc.perform(put("/api/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePatient() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        int databaseSizeBeforeDelete = patientRepository.findAll().size();

        // Delete the patient
        restPatientMockMvc.perform(delete("/api/patients/{id}", patient.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
