package org.bbmp.chbms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.bbmp.chbms.web.rest.TestUtil;

public class HospitalAuditTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HospitalAudit.class);
        HospitalAudit hospitalAudit1 = new HospitalAudit();
        hospitalAudit1.setId(1L);
        HospitalAudit hospitalAudit2 = new HospitalAudit();
        hospitalAudit2.setId(hospitalAudit1.getId());
        assertThat(hospitalAudit1).isEqualTo(hospitalAudit2);
        hospitalAudit2.setId(2L);
        assertThat(hospitalAudit1).isNotEqualTo(hospitalAudit2);
        hospitalAudit1.setId(null);
        assertThat(hospitalAudit1).isNotEqualTo(hospitalAudit2);
    }
}
