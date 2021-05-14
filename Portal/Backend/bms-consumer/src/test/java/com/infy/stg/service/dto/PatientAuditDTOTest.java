package com.infy.stg.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.infy.stg.web.rest.TestUtil;

public class PatientAuditDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientAuditDTO.class);
        PatientAuditDTO patientAuditDTO1 = new PatientAuditDTO();
        patientAuditDTO1.setId(1L);
        PatientAuditDTO patientAuditDTO2 = new PatientAuditDTO();
        assertThat(patientAuditDTO1).isNotEqualTo(patientAuditDTO2);
        patientAuditDTO2.setId(patientAuditDTO1.getId());
        assertThat(patientAuditDTO1).isEqualTo(patientAuditDTO2);
        patientAuditDTO2.setId(2L);
        assertThat(patientAuditDTO1).isNotEqualTo(patientAuditDTO2);
        patientAuditDTO1.setId(null);
        assertThat(patientAuditDTO1).isNotEqualTo(patientAuditDTO2);
    }
}
