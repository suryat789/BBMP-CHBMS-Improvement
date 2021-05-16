package org.bbmp.chbms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HospitalAuditMapperTest {

    private HospitalAuditMapper hospitalAuditMapper;

    @BeforeEach
    public void setUp() {
        hospitalAuditMapper = new HospitalAuditMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(hospitalAuditMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(hospitalAuditMapper.fromId(null)).isNull();
    }
}
