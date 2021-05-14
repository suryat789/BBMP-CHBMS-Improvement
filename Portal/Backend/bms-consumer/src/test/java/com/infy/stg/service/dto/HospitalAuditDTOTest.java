package com.infy.stg.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.infy.stg.web.rest.TestUtil;

public class HospitalAuditDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HospitalAuditDTO.class);
        HospitalAuditDTO hospitalAuditDTO1 = new HospitalAuditDTO();
        hospitalAuditDTO1.setId(1L);
        HospitalAuditDTO hospitalAuditDTO2 = new HospitalAuditDTO();
        assertThat(hospitalAuditDTO1).isNotEqualTo(hospitalAuditDTO2);
        hospitalAuditDTO2.setId(hospitalAuditDTO1.getId());
        assertThat(hospitalAuditDTO1).isEqualTo(hospitalAuditDTO2);
        hospitalAuditDTO2.setId(2L);
        assertThat(hospitalAuditDTO1).isNotEqualTo(hospitalAuditDTO2);
        hospitalAuditDTO1.setId(null);
        assertThat(hospitalAuditDTO1).isNotEqualTo(hospitalAuditDTO2);
    }
}
