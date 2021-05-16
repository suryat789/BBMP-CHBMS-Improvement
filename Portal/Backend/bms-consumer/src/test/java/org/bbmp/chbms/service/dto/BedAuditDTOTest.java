package org.bbmp.chbms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.bbmp.chbms.web.rest.TestUtil;

public class BedAuditDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BedAuditDTO.class);
        BedAuditDTO bedAuditDTO1 = new BedAuditDTO();
        bedAuditDTO1.setId(1L);
        BedAuditDTO bedAuditDTO2 = new BedAuditDTO();
        assertThat(bedAuditDTO1).isNotEqualTo(bedAuditDTO2);
        bedAuditDTO2.setId(bedAuditDTO1.getId());
        assertThat(bedAuditDTO1).isEqualTo(bedAuditDTO2);
        bedAuditDTO2.setId(2L);
        assertThat(bedAuditDTO1).isNotEqualTo(bedAuditDTO2);
        bedAuditDTO1.setId(null);
        assertThat(bedAuditDTO1).isNotEqualTo(bedAuditDTO2);
    }
}
