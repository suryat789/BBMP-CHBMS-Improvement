package com.infy.stg.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.infy.stg.web.rest.TestUtil;

public class BedAuditTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BedAudit.class);
        BedAudit bedAudit1 = new BedAudit();
        bedAudit1.setId(1L);
        BedAudit bedAudit2 = new BedAudit();
        bedAudit2.setId(bedAudit1.getId());
        assertThat(bedAudit1).isEqualTo(bedAudit2);
        bedAudit2.setId(2L);
        assertThat(bedAudit1).isNotEqualTo(bedAudit2);
        bedAudit1.setId(null);
        assertThat(bedAudit1).isNotEqualTo(bedAudit2);
    }
}
