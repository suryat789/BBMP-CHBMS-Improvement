package org.bbmp.chbms.web.rest;

import org.bbmp.chbms.ChbmsApp;
import org.bbmp.chbms.domain.BedAudit;
import org.bbmp.chbms.repository.BedAuditRepository;
import org.bbmp.chbms.service.BedAuditService;
import org.bbmp.chbms.service.dto.BedAuditDTO;
import org.bbmp.chbms.service.mapper.BedAuditMapper;

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
 * Integration tests for the {@link BedAuditResource} REST controller.
 */
@SpringBootTest(classes = ChbmsApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BedAuditResourceIT {

    private static final String DEFAULT_HOSPITAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_HOSPITAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BED_ID = "AAAAAAAAAA";
    private static final String UPDATED_BED_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CAPACITY = 1;
    private static final Integer UPDATED_CAPACITY = 2;

    private static final Integer DEFAULT_OCCUPIED = 1;
    private static final Integer UPDATED_OCCUPIED = 2;

    private static final Integer DEFAULT_BLOCKED = 1;
    private static final Integer UPDATED_BLOCKED = 2;

    private static final Integer DEFAULT_VACANT = 1;
    private static final Integer UPDATED_VACANT = 2;

    private static final Instant DEFAULT_CREATED_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATED_BY_MSG_ID = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_MSG_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_AUDITED_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_AUDITED_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private BedAuditRepository bedAuditRepository;

    @Autowired
    private BedAuditMapper bedAuditMapper;

    @Autowired
    private BedAuditService bedAuditService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBedAuditMockMvc;

    private BedAudit bedAudit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BedAudit createEntity(EntityManager em) {
        BedAudit bedAudit = new BedAudit()
            .hospitalId(DEFAULT_HOSPITAL_ID)
            .bedId(DEFAULT_BED_ID)
            .type(DEFAULT_TYPE)
            .capacity(DEFAULT_CAPACITY)
            .occupied(DEFAULT_OCCUPIED)
            .blocked(DEFAULT_BLOCKED)
            .vacant(DEFAULT_VACANT)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedOn(DEFAULT_UPDATED_ON)
            .updatedByMsgId(DEFAULT_UPDATED_BY_MSG_ID)
            .auditedOn(DEFAULT_AUDITED_ON);
        return bedAudit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BedAudit createUpdatedEntity(EntityManager em) {
        BedAudit bedAudit = new BedAudit()
            .hospitalId(UPDATED_HOSPITAL_ID)
            .bedId(UPDATED_BED_ID)
            .type(UPDATED_TYPE)
            .capacity(UPDATED_CAPACITY)
            .occupied(UPDATED_OCCUPIED)
            .blocked(UPDATED_BLOCKED)
            .vacant(UPDATED_VACANT)
            .createdOn(UPDATED_CREATED_ON)
            .updatedOn(UPDATED_UPDATED_ON)
            .updatedByMsgId(UPDATED_UPDATED_BY_MSG_ID)
            .auditedOn(UPDATED_AUDITED_ON);
        return bedAudit;
    }

    @BeforeEach
    public void initTest() {
        bedAudit = createEntity(em);
    }

    @Test
    @Transactional
    public void createBedAudit() throws Exception {
        int databaseSizeBeforeCreate = bedAuditRepository.findAll().size();
        // Create the BedAudit
        BedAuditDTO bedAuditDTO = bedAuditMapper.toDto(bedAudit);
        restBedAuditMockMvc.perform(post("/api/bed-audits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bedAuditDTO)))
            .andExpect(status().isCreated());

        // Validate the BedAudit in the database
        List<BedAudit> bedAuditList = bedAuditRepository.findAll();
        assertThat(bedAuditList).hasSize(databaseSizeBeforeCreate + 1);
        BedAudit testBedAudit = bedAuditList.get(bedAuditList.size() - 1);
        assertThat(testBedAudit.getHospitalId()).isEqualTo(DEFAULT_HOSPITAL_ID);
        assertThat(testBedAudit.getBedId()).isEqualTo(DEFAULT_BED_ID);
        assertThat(testBedAudit.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testBedAudit.getCapacity()).isEqualTo(DEFAULT_CAPACITY);
        assertThat(testBedAudit.getOccupied()).isEqualTo(DEFAULT_OCCUPIED);
        assertThat(testBedAudit.getBlocked()).isEqualTo(DEFAULT_BLOCKED);
        assertThat(testBedAudit.getVacant()).isEqualTo(DEFAULT_VACANT);
        assertThat(testBedAudit.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testBedAudit.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
        assertThat(testBedAudit.getUpdatedByMsgId()).isEqualTo(DEFAULT_UPDATED_BY_MSG_ID);
        assertThat(testBedAudit.getAuditedOn()).isEqualTo(DEFAULT_AUDITED_ON);
    }

    @Test
    @Transactional
    public void createBedAuditWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bedAuditRepository.findAll().size();

        // Create the BedAudit with an existing ID
        bedAudit.setId(1L);
        BedAuditDTO bedAuditDTO = bedAuditMapper.toDto(bedAudit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBedAuditMockMvc.perform(post("/api/bed-audits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bedAuditDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BedAudit in the database
        List<BedAudit> bedAuditList = bedAuditRepository.findAll();
        assertThat(bedAuditList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBedAudits() throws Exception {
        // Initialize the database
        bedAuditRepository.saveAndFlush(bedAudit);

        // Get all the bedAuditList
        restBedAuditMockMvc.perform(get("/api/bed-audits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bedAudit.getId().intValue())))
            .andExpect(jsonPath("$.[*].hospitalId").value(hasItem(DEFAULT_HOSPITAL_ID)))
            .andExpect(jsonPath("$.[*].bedId").value(hasItem(DEFAULT_BED_ID)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].capacity").value(hasItem(DEFAULT_CAPACITY)))
            .andExpect(jsonPath("$.[*].occupied").value(hasItem(DEFAULT_OCCUPIED)))
            .andExpect(jsonPath("$.[*].blocked").value(hasItem(DEFAULT_BLOCKED)))
            .andExpect(jsonPath("$.[*].vacant").value(hasItem(DEFAULT_VACANT)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedByMsgId").value(hasItem(DEFAULT_UPDATED_BY_MSG_ID)))
            .andExpect(jsonPath("$.[*].auditedOn").value(hasItem(DEFAULT_AUDITED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getBedAudit() throws Exception {
        // Initialize the database
        bedAuditRepository.saveAndFlush(bedAudit);

        // Get the bedAudit
        restBedAuditMockMvc.perform(get("/api/bed-audits/{id}", bedAudit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bedAudit.getId().intValue()))
            .andExpect(jsonPath("$.hospitalId").value(DEFAULT_HOSPITAL_ID))
            .andExpect(jsonPath("$.bedId").value(DEFAULT_BED_ID))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.capacity").value(DEFAULT_CAPACITY))
            .andExpect(jsonPath("$.occupied").value(DEFAULT_OCCUPIED))
            .andExpect(jsonPath("$.blocked").value(DEFAULT_BLOCKED))
            .andExpect(jsonPath("$.vacant").value(DEFAULT_VACANT))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()))
            .andExpect(jsonPath("$.updatedByMsgId").value(DEFAULT_UPDATED_BY_MSG_ID))
            .andExpect(jsonPath("$.auditedOn").value(DEFAULT_AUDITED_ON.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBedAudit() throws Exception {
        // Get the bedAudit
        restBedAuditMockMvc.perform(get("/api/bed-audits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBedAudit() throws Exception {
        // Initialize the database
        bedAuditRepository.saveAndFlush(bedAudit);

        int databaseSizeBeforeUpdate = bedAuditRepository.findAll().size();

        // Update the bedAudit
        BedAudit updatedBedAudit = bedAuditRepository.findById(bedAudit.getId()).get();
        // Disconnect from session so that the updates on updatedBedAudit are not directly saved in db
        em.detach(updatedBedAudit);
        updatedBedAudit
            .hospitalId(UPDATED_HOSPITAL_ID)
            .bedId(UPDATED_BED_ID)
            .type(UPDATED_TYPE)
            .capacity(UPDATED_CAPACITY)
            .occupied(UPDATED_OCCUPIED)
            .blocked(UPDATED_BLOCKED)
            .vacant(UPDATED_VACANT)
            .createdOn(UPDATED_CREATED_ON)
            .updatedOn(UPDATED_UPDATED_ON)
            .updatedByMsgId(UPDATED_UPDATED_BY_MSG_ID)
            .auditedOn(UPDATED_AUDITED_ON);
        BedAuditDTO bedAuditDTO = bedAuditMapper.toDto(updatedBedAudit);

        restBedAuditMockMvc.perform(put("/api/bed-audits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bedAuditDTO)))
            .andExpect(status().isOk());

        // Validate the BedAudit in the database
        List<BedAudit> bedAuditList = bedAuditRepository.findAll();
        assertThat(bedAuditList).hasSize(databaseSizeBeforeUpdate);
        BedAudit testBedAudit = bedAuditList.get(bedAuditList.size() - 1);
        assertThat(testBedAudit.getHospitalId()).isEqualTo(UPDATED_HOSPITAL_ID);
        assertThat(testBedAudit.getBedId()).isEqualTo(UPDATED_BED_ID);
        assertThat(testBedAudit.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testBedAudit.getCapacity()).isEqualTo(UPDATED_CAPACITY);
        assertThat(testBedAudit.getOccupied()).isEqualTo(UPDATED_OCCUPIED);
        assertThat(testBedAudit.getBlocked()).isEqualTo(UPDATED_BLOCKED);
        assertThat(testBedAudit.getVacant()).isEqualTo(UPDATED_VACANT);
        assertThat(testBedAudit.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testBedAudit.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
        assertThat(testBedAudit.getUpdatedByMsgId()).isEqualTo(UPDATED_UPDATED_BY_MSG_ID);
        assertThat(testBedAudit.getAuditedOn()).isEqualTo(UPDATED_AUDITED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingBedAudit() throws Exception {
        int databaseSizeBeforeUpdate = bedAuditRepository.findAll().size();

        // Create the BedAudit
        BedAuditDTO bedAuditDTO = bedAuditMapper.toDto(bedAudit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBedAuditMockMvc.perform(put("/api/bed-audits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bedAuditDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BedAudit in the database
        List<BedAudit> bedAuditList = bedAuditRepository.findAll();
        assertThat(bedAuditList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBedAudit() throws Exception {
        // Initialize the database
        bedAuditRepository.saveAndFlush(bedAudit);

        int databaseSizeBeforeDelete = bedAuditRepository.findAll().size();

        // Delete the bedAudit
        restBedAuditMockMvc.perform(delete("/api/bed-audits/{id}", bedAudit.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BedAudit> bedAuditList = bedAuditRepository.findAll();
        assertThat(bedAuditList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
