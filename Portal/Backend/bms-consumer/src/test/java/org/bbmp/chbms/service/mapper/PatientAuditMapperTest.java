package org.bbmp.chbms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PatientAuditMapperTest {

    private PatientAuditMapper patientAuditMapper;

    @BeforeEach
    public void setUp() {
        patientAuditMapper = new PatientAuditMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(patientAuditMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(patientAuditMapper.fromId(null)).isNull();
    }
}
