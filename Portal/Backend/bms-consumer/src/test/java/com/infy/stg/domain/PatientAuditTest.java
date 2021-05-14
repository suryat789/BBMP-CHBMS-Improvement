package com.infy.stg.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.infy.stg.web.rest.TestUtil;

public class PatientAuditTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientAudit.class);
        PatientAudit patientAudit1 = new PatientAudit();
        patientAudit1.setId(1L);
        PatientAudit patientAudit2 = new PatientAudit();
        patientAudit2.setId(patientAudit1.getId());
        assertThat(patientAudit1).isEqualTo(patientAudit2);
        patientAudit2.setId(2L);
        assertThat(patientAudit1).isNotEqualTo(patientAudit2);
        patientAudit1.setId(null);
        assertThat(patientAudit1).isNotEqualTo(patientAudit2);
    }
}
