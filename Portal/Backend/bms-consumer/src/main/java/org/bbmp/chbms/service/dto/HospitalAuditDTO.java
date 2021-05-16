package org.bbmp.chbms.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link org.bbmp.chbms.domain.HospitalAudit} entity.
 */
public class HospitalAuditDTO implements Serializable {
    
    private Long id;

    private String hospitalId;

    private String type;

    private String name;

    private String address;

    private Long pincode;

    private String phone;

    private String zone;

    private String status;

    private Instant createdOn;

    private Instant updatedOn;

    private String updatedByMsgId;

    private Instant auditedOn;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedByMsgId() {
        return updatedByMsgId;
    }

    public void setUpdatedByMsgId(String updatedByMsgId) {
        this.updatedByMsgId = updatedByMsgId;
    }

    public Instant getAuditedOn() {
        return auditedOn;
    }

    public void setAuditedOn(Instant auditedOn) {
        this.auditedOn = auditedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HospitalAuditDTO)) {
            return false;
        }

        return id != null && id.equals(((HospitalAuditDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HospitalAuditDTO{" +
            "id=" + getId() +
            ", hospitalId='" + getHospitalId() + "'" +
            ", type='" + getType() + "'" +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", pincode=" + getPincode() +
            ", phone='" + getPhone() + "'" +
            ", zone='" + getZone() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            ", updatedByMsgId='" + getUpdatedByMsgId() + "'" +
            ", auditedOn='" + getAuditedOn() + "'" +
            "}";
    }
}
