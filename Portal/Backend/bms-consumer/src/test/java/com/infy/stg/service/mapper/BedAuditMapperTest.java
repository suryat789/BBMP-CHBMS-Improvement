package com.infy.stg.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BedAuditMapperTest {

    private BedAuditMapper bedAuditMapper;

    @BeforeEach
    public void setUp() {
        bedAuditMapper = new BedAuditMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(bedAuditMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(bedAuditMapper.fromId(null)).isNull();
    }
}
