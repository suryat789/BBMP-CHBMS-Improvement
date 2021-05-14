package com.infy.stg.web.rest;

import com.infy.stg.BmsApp;
import com.infy.stg.domain.PatientAudit;
import com.infy.stg.repository.PatientAuditRepository;
import com.infy.stg.service.PatientAuditService;
import com.infy.stg.service.dto.PatientAuditDTO;
import com.infy.stg.service.mapper.PatientAuditMapper;

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
 * Integration tests for the {@link PatientAuditResource} REST controller.
 */
@SpringBootTest(classes = BmsApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PatientAuditResourceIT {

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

    private static final Instant DEFAULT_AUDITED_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_AUDITED_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PatientAuditRepository patientAuditRepository;

    @Autowired
    private PatientAuditMapper patientAuditMapper;

    @Autowired
    private PatientAuditService patientAuditService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPatientAuditMockMvc;

    private PatientAudit patientAudit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientAudit createEntity(EntityManager em) {
        PatientAudit patientAudit = new PatientAudit()
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
            .updatedByMsgId(DEFAULT_UPDATED_BY_MSG_ID)
            .auditedOn(DEFAULT_AUDITED_ON);
        return patientAudit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientAudit createUpdatedEntity(EntityManager em) {
        PatientAudit patientAudit = new PatientAudit()
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
            .updatedByMsgId(UPDATED_UPDATED_BY_MSG_ID)
            .auditedOn(UPDATED_AUDITED_ON);
        return patientAudit;
    }

    @BeforeEach
    public void initTest() {
        patientAudit = createEntity(em);
    }

    @Test
    @Transactional
    public void createPatientAudit() throws Exception {
        int databaseSizeBeforeCreate = patientAuditRepository.findAll().size();
        // Create the PatientAudit
        PatientAuditDTO patientAuditDTO = patientAuditMapper.toDto(patientAudit);
        restPatientAuditMockMvc.perform(post("/api/patient-audits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientAuditDTO)))
            .andExpect(status().isCreated());

        // Validate the PatientAudit in the database
        List<PatientAudit> patientAuditList = patientAuditRepository.findAll();
        assertThat(patientAuditList).hasSize(databaseSizeBeforeCreate + 1);
        PatientAudit testPatientAudit = patientAuditList.get(patientAuditList.size() - 1);
        assertThat(testPatientAudit.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testPatientAudit.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testPatientAudit.getSrfNumber()).isEqualTo(DEFAULT_SRF_NUMBER);
        assertThat(testPatientAudit.getBucode()).isEqualTo(DEFAULT_BUCODE);
        assertThat(testPatientAudit.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testPatientAudit.getZone()).isEqualTo(DEFAULT_ZONE);
        assertThat(testPatientAudit.getQueueType()).isEqualTo(DEFAULT_QUEUE_TYPE);
        assertThat(testPatientAudit.getQueueName()).isEqualTo(DEFAULT_QUEUE_NAME);
        assertThat(testPatientAudit.getTimeAddedToQueue()).isEqualTo(DEFAULT_TIME_ADDED_TO_QUEUE);
        assertThat(testPatientAudit.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testPatientAudit.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
        assertThat(testPatientAudit.getUpdatedByMsgId()).isEqualTo(DEFAULT_UPDATED_BY_MSG_ID);
        assertThat(testPatientAudit.getAuditedOn()).isEqualTo(DEFAULT_AUDITED_ON);
    }

    @Test
    @Transactional
    public void createPatientAuditWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = patientAuditRepository.findAll().size();

        // Create the PatientAudit with an existing ID
        patientAudit.setId(1L);
        PatientAuditDTO patientAuditDTO = patientAuditMapper.toDto(patientAudit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPatientAuditMockMvc.perform(post("/api/patient-audits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientAuditDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PatientAudit in the database
        List<PatientAudit> patientAuditList = patientAuditRepository.findAll();
        assertThat(patientAuditList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPatientAudits() throws Exception {
        // Initialize the database
        patientAuditRepository.saveAndFlush(patientAudit);

        // Get all the patientAuditList
        restPatientAuditMockMvc.perform(get("/api/patient-audits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(patientAudit.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].updatedByMsgId").value(hasItem(DEFAULT_UPDATED_BY_MSG_ID)))
            .andExpect(jsonPath("$.[*].auditedOn").value(hasItem(DEFAULT_AUDITED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getPatientAudit() throws Exception {
        // Initialize the database
        patientAuditRepository.saveAndFlush(patientAudit);

        // Get the patientAudit
        restPatientAuditMockMvc.perform(get("/api/patient-audits/{id}", patientAudit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(patientAudit.getId().intValue()))
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
            .andExpect(jsonPath("$.updatedByMsgId").value(DEFAULT_UPDATED_BY_MSG_ID))
            .andExpect(jsonPath("$.auditedOn").value(DEFAULT_AUDITED_ON.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPatientAudit() throws Exception {
        // Get the patientAudit
        restPatientAuditMockMvc.perform(get("/api/patient-audits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePatientAudit() throws Exception {
        // Initialize the database
        patientAuditRepository.saveAndFlush(patientAudit);

        int databaseSizeBeforeUpdate = patientAuditRepository.findAll().size();

        // Update the patientAudit
        PatientAudit updatedPatientAudit = patientAuditRepository.findById(patientAudit.getId()).get();
        // Disconnect from session so that the updates on updatedPatientAudit are not directly saved in db
        em.detach(updatedPatientAudit);
        updatedPatientAudit
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
            .updatedByMsgId(UPDATED_UPDATED_BY_MSG_ID)
            .auditedOn(UPDATED_AUDITED_ON);
        PatientAuditDTO patientAuditDTO = patientAuditMapper.toDto(updatedPatientAudit);

        restPatientAuditMockMvc.perform(put("/api/patient-audits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientAuditDTO)))
            .andExpect(status().isOk());

        // Validate the PatientAudit in the database
        List<PatientAudit> patientAuditList = patientAuditRepository.findAll();
        assertThat(patientAuditList).hasSize(databaseSizeBeforeUpdate);
        PatientAudit testPatientAudit = patientAuditList.get(patientAuditList.size() - 1);
        assertThat(testPatientAudit.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientAudit.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testPatientAudit.getSrfNumber()).isEqualTo(UPDATED_SRF_NUMBER);
        assertThat(testPatientAudit.getBucode()).isEqualTo(UPDATED_BUCODE);
        assertThat(testPatientAudit.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testPatientAudit.getZone()).isEqualTo(UPDATED_ZONE);
        assertThat(testPatientAudit.getQueueType()).isEqualTo(UPDATED_QUEUE_TYPE);
        assertThat(testPatientAudit.getQueueName()).isEqualTo(UPDATED_QUEUE_NAME);
        assertThat(testPatientAudit.getTimeAddedToQueue()).isEqualTo(UPDATED_TIME_ADDED_TO_QUEUE);
        assertThat(testPatientAudit.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testPatientAudit.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
        assertThat(testPatientAudit.getUpdatedByMsgId()).isEqualTo(UPDATED_UPDATED_BY_MSG_ID);
        assertThat(testPatientAudit.getAuditedOn()).isEqualTo(UPDATED_AUDITED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingPatientAudit() throws Exception {
        int databaseSizeBeforeUpdate = patientAuditRepository.findAll().size();

        // Create the PatientAudit
        PatientAuditDTO patientAuditDTO = patientAuditMapper.toDto(patientAudit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPatientAuditMockMvc.perform(put("/api/patient-audits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientAuditDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PatientAudit in the database
        List<PatientAudit> patientAuditList = patientAuditRepository.findAll();
        assertThat(patientAuditList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePatientAudit() throws Exception {
        // Initialize the database
        patientAuditRepository.saveAndFlush(patientAudit);

        int databaseSizeBeforeDelete = patientAuditRepository.findAll().size();

        // Delete the patientAudit
        restPatientAuditMockMvc.perform(delete("/api/patient-audits/{id}", patientAudit.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PatientAudit> patientAuditList = patientAuditRepository.findAll();
        assertThat(patientAuditList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
