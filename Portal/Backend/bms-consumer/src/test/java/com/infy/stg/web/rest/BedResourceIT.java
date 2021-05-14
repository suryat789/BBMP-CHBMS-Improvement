package com.infy.stg.web.rest;

import com.infy.stg.BmsApp;
import com.infy.stg.domain.Bed;
import com.infy.stg.repository.BedRepository;
import com.infy.stg.service.BedService;
import com.infy.stg.service.dto.BedDTO;
import com.infy.stg.service.mapper.BedMapper;

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
 * Integration tests for the {@link BedResource} REST controller.
 */
@SpringBootTest(classes = BmsApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BedResourceIT {

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

    @Autowired
    private BedRepository bedRepository;

    @Autowired
    private BedMapper bedMapper;

    @Autowired
    private BedService bedService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBedMockMvc;

    private Bed bed;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bed createEntity(EntityManager em) {
        Bed bed = new Bed()
            .bedId(DEFAULT_BED_ID)
            .type(DEFAULT_TYPE)
            .capacity(DEFAULT_CAPACITY)
            .occupied(DEFAULT_OCCUPIED)
            .blocked(DEFAULT_BLOCKED)
            .vacant(DEFAULT_VACANT)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedOn(DEFAULT_UPDATED_ON)
            .updatedByMsgId(DEFAULT_UPDATED_BY_MSG_ID);
        return bed;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bed createUpdatedEntity(EntityManager em) {
        Bed bed = new Bed()
            .bedId(UPDATED_BED_ID)
            .type(UPDATED_TYPE)
            .capacity(UPDATED_CAPACITY)
            .occupied(UPDATED_OCCUPIED)
            .blocked(UPDATED_BLOCKED)
            .vacant(UPDATED_VACANT)
            .createdOn(UPDATED_CREATED_ON)
            .updatedOn(UPDATED_UPDATED_ON)
            .updatedByMsgId(UPDATED_UPDATED_BY_MSG_ID);
        return bed;
    }

    @BeforeEach
    public void initTest() {
        bed = createEntity(em);
    }

    @Test
    @Transactional
    public void createBed() throws Exception {
        int databaseSizeBeforeCreate = bedRepository.findAll().size();
        // Create the Bed
        BedDTO bedDTO = bedMapper.toDto(bed);
        restBedMockMvc.perform(post("/api/beds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bedDTO)))
            .andExpect(status().isCreated());

        // Validate the Bed in the database
        List<Bed> bedList = bedRepository.findAll();
        assertThat(bedList).hasSize(databaseSizeBeforeCreate + 1);
        Bed testBed = bedList.get(bedList.size() - 1);
        assertThat(testBed.getBedId()).isEqualTo(DEFAULT_BED_ID);
        assertThat(testBed.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testBed.getCapacity()).isEqualTo(DEFAULT_CAPACITY);
        assertThat(testBed.getOccupied()).isEqualTo(DEFAULT_OCCUPIED);
        assertThat(testBed.getBlocked()).isEqualTo(DEFAULT_BLOCKED);
        assertThat(testBed.getVacant()).isEqualTo(DEFAULT_VACANT);
        assertThat(testBed.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testBed.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
        assertThat(testBed.getUpdatedByMsgId()).isEqualTo(DEFAULT_UPDATED_BY_MSG_ID);
    }

    @Test
    @Transactional
    public void createBedWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bedRepository.findAll().size();

        // Create the Bed with an existing ID
        bed.setId(1L);
        BedDTO bedDTO = bedMapper.toDto(bed);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBedMockMvc.perform(post("/api/beds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bed in the database
        List<Bed> bedList = bedRepository.findAll();
        assertThat(bedList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBeds() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList
        restBedMockMvc.perform(get("/api/beds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bed.getId().intValue())))
            .andExpect(jsonPath("$.[*].bedId").value(hasItem(DEFAULT_BED_ID)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].capacity").value(hasItem(DEFAULT_CAPACITY)))
            .andExpect(jsonPath("$.[*].occupied").value(hasItem(DEFAULT_OCCUPIED)))
            .andExpect(jsonPath("$.[*].blocked").value(hasItem(DEFAULT_BLOCKED)))
            .andExpect(jsonPath("$.[*].vacant").value(hasItem(DEFAULT_VACANT)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedByMsgId").value(hasItem(DEFAULT_UPDATED_BY_MSG_ID)));
    }
    
    @Test
    @Transactional
    public void getBed() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get the bed
        restBedMockMvc.perform(get("/api/beds/{id}", bed.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bed.getId().intValue()))
            .andExpect(jsonPath("$.bedId").value(DEFAULT_BED_ID))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.capacity").value(DEFAULT_CAPACITY))
            .andExpect(jsonPath("$.occupied").value(DEFAULT_OCCUPIED))
            .andExpect(jsonPath("$.blocked").value(DEFAULT_BLOCKED))
            .andExpect(jsonPath("$.vacant").value(DEFAULT_VACANT))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()))
            .andExpect(jsonPath("$.updatedByMsgId").value(DEFAULT_UPDATED_BY_MSG_ID));
    }
    @Test
    @Transactional
    public void getNonExistingBed() throws Exception {
        // Get the bed
        restBedMockMvc.perform(get("/api/beds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBed() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        int databaseSizeBeforeUpdate = bedRepository.findAll().size();

        // Update the bed
        Bed updatedBed = bedRepository.findById(bed.getId()).get();
        // Disconnect from session so that the updates on updatedBed are not directly saved in db
        em.detach(updatedBed);
        updatedBed
            .bedId(UPDATED_BED_ID)
            .type(UPDATED_TYPE)
            .capacity(UPDATED_CAPACITY)
            .occupied(UPDATED_OCCUPIED)
            .blocked(UPDATED_BLOCKED)
            .vacant(UPDATED_VACANT)
            .createdOn(UPDATED_CREATED_ON)
            .updatedOn(UPDATED_UPDATED_ON)
            .updatedByMsgId(UPDATED_UPDATED_BY_MSG_ID);
        BedDTO bedDTO = bedMapper.toDto(updatedBed);

        restBedMockMvc.perform(put("/api/beds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bedDTO)))
            .andExpect(status().isOk());

        // Validate the Bed in the database
        List<Bed> bedList = bedRepository.findAll();
        assertThat(bedList).hasSize(databaseSizeBeforeUpdate);
        Bed testBed = bedList.get(bedList.size() - 1);
        assertThat(testBed.getBedId()).isEqualTo(UPDATED_BED_ID);
        assertThat(testBed.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testBed.getCapacity()).isEqualTo(UPDATED_CAPACITY);
        assertThat(testBed.getOccupied()).isEqualTo(UPDATED_OCCUPIED);
        assertThat(testBed.getBlocked()).isEqualTo(UPDATED_BLOCKED);
        assertThat(testBed.getVacant()).isEqualTo(UPDATED_VACANT);
        assertThat(testBed.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testBed.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
        assertThat(testBed.getUpdatedByMsgId()).isEqualTo(UPDATED_UPDATED_BY_MSG_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingBed() throws Exception {
        int databaseSizeBeforeUpdate = bedRepository.findAll().size();

        // Create the Bed
        BedDTO bedDTO = bedMapper.toDto(bed);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBedMockMvc.perform(put("/api/beds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bed in the database
        List<Bed> bedList = bedRepository.findAll();
        assertThat(bedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBed() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        int databaseSizeBeforeDelete = bedRepository.findAll().size();

        // Delete the bed
        restBedMockMvc.perform(delete("/api/beds/{id}", bed.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bed> bedList = bedRepository.findAll();
        assertThat(bedList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
