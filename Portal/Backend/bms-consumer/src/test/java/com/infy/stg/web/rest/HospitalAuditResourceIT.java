package com.infy.stg.web.rest;

import com.infy.stg.BmsApp;
import com.infy.stg.domain.HospitalAudit;
import com.infy.stg.repository.HospitalAuditRepository;
import com.infy.stg.service.HospitalAuditService;
import com.infy.stg.service.dto.HospitalAuditDTO;
import com.infy.stg.service.mapper.HospitalAuditMapper;

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
 * Integration tests for the {@link HospitalAuditResource} REST controller.
 */
@SpringBootTest(classes = BmsApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class HospitalAuditResourceIT {

    private static final String DEFAULT_HOSPITAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_HOSPITAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Long DEFAULT_PINCODE = 1L;
    private static final Long UPDATED_PINCODE = 2L;

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_ZONE = "AAAAAAAAAA";
    private static final String UPDATED_ZONE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATED_BY_MSG_ID = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_MSG_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_AUDITED_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_AUDITED_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private HospitalAuditRepository hospitalAuditRepository;

    @Autowired
    private HospitalAuditMapper hospitalAuditMapper;

    @Autowired
    private HospitalAuditService hospitalAuditService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHospitalAuditMockMvc;

    private HospitalAudit hospitalAudit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HospitalAudit createEntity(EntityManager em) {
        HospitalAudit hospitalAudit = new HospitalAudit()
            .hospitalId(DEFAULT_HOSPITAL_ID)
            .type(DEFAULT_TYPE)
            .name(DEFAULT_NAME)
            .address(DEFAULT_ADDRESS)
            .pincode(DEFAULT_PINCODE)
            .phone(DEFAULT_PHONE)
            .zone(DEFAULT_ZONE)
            .status(DEFAULT_STATUS)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedOn(DEFAULT_UPDATED_ON)
            .updatedByMsgId(DEFAULT_UPDATED_BY_MSG_ID)
            .auditedOn(DEFAULT_AUDITED_ON);
        return hospitalAudit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HospitalAudit createUpdatedEntity(EntityManager em) {
        HospitalAudit hospitalAudit = new HospitalAudit()
            .hospitalId(UPDATED_HOSPITAL_ID)
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .pincode(UPDATED_PINCODE)
            .phone(UPDATED_PHONE)
            .zone(UPDATED_ZONE)
            .status(UPDATED_STATUS)
            .createdOn(UPDATED_CREATED_ON)
            .updatedOn(UPDATED_UPDATED_ON)
            .updatedByMsgId(UPDATED_UPDATED_BY_MSG_ID)
            .auditedOn(UPDATED_AUDITED_ON);
        return hospitalAudit;
    }

    @BeforeEach
    public void initTest() {
        hospitalAudit = createEntity(em);
    }

    @Test
    @Transactional
    public void createHospitalAudit() throws Exception {
        int databaseSizeBeforeCreate = hospitalAuditRepository.findAll().size();
        // Create the HospitalAudit
        HospitalAuditDTO hospitalAuditDTO = hospitalAuditMapper.toDto(hospitalAudit);
        restHospitalAuditMockMvc.perform(post("/api/hospital-audits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(hospitalAuditDTO)))
            .andExpect(status().isCreated());

        // Validate the HospitalAudit in the database
        List<HospitalAudit> hospitalAuditList = hospitalAuditRepository.findAll();
        assertThat(hospitalAuditList).hasSize(databaseSizeBeforeCreate + 1);
        HospitalAudit testHospitalAudit = hospitalAuditList.get(hospitalAuditList.size() - 1);
        assertThat(testHospitalAudit.getHospitalId()).isEqualTo(DEFAULT_HOSPITAL_ID);
        assertThat(testHospitalAudit.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testHospitalAudit.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testHospitalAudit.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testHospitalAudit.getPincode()).isEqualTo(DEFAULT_PINCODE);
        assertThat(testHospitalAudit.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testHospitalAudit.getZone()).isEqualTo(DEFAULT_ZONE);
        assertThat(testHospitalAudit.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testHospitalAudit.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testHospitalAudit.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
        assertThat(testHospitalAudit.getUpdatedByMsgId()).isEqualTo(DEFAULT_UPDATED_BY_MSG_ID);
        assertThat(testHospitalAudit.getAuditedOn()).isEqualTo(DEFAULT_AUDITED_ON);
    }

    @Test
    @Transactional
    public void createHospitalAuditWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hospitalAuditRepository.findAll().size();

        // Create the HospitalAudit with an existing ID
        hospitalAudit.setId(1L);
        HospitalAuditDTO hospitalAuditDTO = hospitalAuditMapper.toDto(hospitalAudit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHospitalAuditMockMvc.perform(post("/api/hospital-audits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(hospitalAuditDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HospitalAudit in the database
        List<HospitalAudit> hospitalAuditList = hospitalAuditRepository.findAll();
        assertThat(hospitalAuditList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllHospitalAudits() throws Exception {
        // Initialize the database
        hospitalAuditRepository.saveAndFlush(hospitalAudit);

        // Get all the hospitalAuditList
        restHospitalAuditMockMvc.perform(get("/api/hospital-audits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hospitalAudit.getId().intValue())))
            .andExpect(jsonPath("$.[*].hospitalId").value(hasItem(DEFAULT_HOSPITAL_ID)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE.intValue())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].zone").value(hasItem(DEFAULT_ZONE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedByMsgId").value(hasItem(DEFAULT_UPDATED_BY_MSG_ID)))
            .andExpect(jsonPath("$.[*].auditedOn").value(hasItem(DEFAULT_AUDITED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getHospitalAudit() throws Exception {
        // Initialize the database
        hospitalAuditRepository.saveAndFlush(hospitalAudit);

        // Get the hospitalAudit
        restHospitalAuditMockMvc.perform(get("/api/hospital-audits/{id}", hospitalAudit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hospitalAudit.getId().intValue()))
            .andExpect(jsonPath("$.hospitalId").value(DEFAULT_HOSPITAL_ID))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.pincode").value(DEFAULT_PINCODE.intValue()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.zone").value(DEFAULT_ZONE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()))
            .andExpect(jsonPath("$.updatedByMsgId").value(DEFAULT_UPDATED_BY_MSG_ID))
            .andExpect(jsonPath("$.auditedOn").value(DEFAULT_AUDITED_ON.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingHospitalAudit() throws Exception {
        // Get the hospitalAudit
        restHospitalAuditMockMvc.perform(get("/api/hospital-audits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHospitalAudit() throws Exception {
        // Initialize the database
        hospitalAuditRepository.saveAndFlush(hospitalAudit);

        int databaseSizeBeforeUpdate = hospitalAuditRepository.findAll().size();

        // Update the hospitalAudit
        HospitalAudit updatedHospitalAudit = hospitalAuditRepository.findById(hospitalAudit.getId()).get();
        // Disconnect from session so that the updates on updatedHospitalAudit are not directly saved in db
        em.detach(updatedHospitalAudit);
        updatedHospitalAudit
            .hospitalId(UPDATED_HOSPITAL_ID)
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .pincode(UPDATED_PINCODE)
            .phone(UPDATED_PHONE)
            .zone(UPDATED_ZONE)
            .status(UPDATED_STATUS)
            .createdOn(UPDATED_CREATED_ON)
            .updatedOn(UPDATED_UPDATED_ON)
            .updatedByMsgId(UPDATED_UPDATED_BY_MSG_ID)
            .auditedOn(UPDATED_AUDITED_ON);
        HospitalAuditDTO hospitalAuditDTO = hospitalAuditMapper.toDto(updatedHospitalAudit);

        restHospitalAuditMockMvc.perform(put("/api/hospital-audits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(hospitalAuditDTO)))
            .andExpect(status().isOk());

        // Validate the HospitalAudit in the database
        List<HospitalAudit> hospitalAuditList = hospitalAuditRepository.findAll();
        assertThat(hospitalAuditList).hasSize(databaseSizeBeforeUpdate);
        HospitalAudit testHospitalAudit = hospitalAuditList.get(hospitalAuditList.size() - 1);
        assertThat(testHospitalAudit.getHospitalId()).isEqualTo(UPDATED_HOSPITAL_ID);
        assertThat(testHospitalAudit.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testHospitalAudit.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testHospitalAudit.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testHospitalAudit.getPincode()).isEqualTo(UPDATED_PINCODE);
        assertThat(testHospitalAudit.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testHospitalAudit.getZone()).isEqualTo(UPDATED_ZONE);
        assertThat(testHospitalAudit.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testHospitalAudit.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testHospitalAudit.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
        assertThat(testHospitalAudit.getUpdatedByMsgId()).isEqualTo(UPDATED_UPDATED_BY_MSG_ID);
        assertThat(testHospitalAudit.getAuditedOn()).isEqualTo(UPDATED_AUDITED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingHospitalAudit() throws Exception {
        int databaseSizeBeforeUpdate = hospitalAuditRepository.findAll().size();

        // Create the HospitalAudit
        HospitalAuditDTO hospitalAuditDTO = hospitalAuditMapper.toDto(hospitalAudit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHospitalAuditMockMvc.perform(put("/api/hospital-audits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(hospitalAuditDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HospitalAudit in the database
        List<HospitalAudit> hospitalAuditList = hospitalAuditRepository.findAll();
        assertThat(hospitalAuditList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHospitalAudit() throws Exception {
        // Initialize the database
        hospitalAuditRepository.saveAndFlush(hospitalAudit);

        int databaseSizeBeforeDelete = hospitalAuditRepository.findAll().size();

        // Delete the hospitalAudit
        restHospitalAuditMockMvc.perform(delete("/api/hospital-audits/{id}", hospitalAudit.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HospitalAudit> hospitalAuditList = hospitalAuditRepository.findAll();
        assertThat(hospitalAuditList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
