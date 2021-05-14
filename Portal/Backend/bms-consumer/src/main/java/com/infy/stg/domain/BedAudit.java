package com.infy.stg.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A BedAudit.
 */
@Entity
@Table(name = "bed_audit")
public class BedAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hospital_id")
    private String hospitalId;

    @Column(name = "bed_id")
    private String bedId;

    @Column(name = "type")
    private String type;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "occupied")
    private Integer occupied;

    @Column(name = "blocked")
    private Integer blocked;

    @Column(name = "vacant")
    private Integer vacant;

    @Column(name = "created_on")
    private Instant createdOn;

    @Column(name = "updated_on")
    private Instant updatedOn;

    @Column(name = "updated_by_msg_id")
    private String updatedByMsgId;

    @Column(name = "audited_on")
    private Instant auditedOn;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public BedAudit hospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
        return this;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getBedId() {
        return bedId;
    }

    public BedAudit bedId(String bedId) {
        this.bedId = bedId;
        return this;
    }

    public void setBedId(String bedId) {
        this.bedId = bedId;
    }

    public String getType() {
        return type;
    }

    public BedAudit type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public BedAudit capacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getOccupied() {
        return occupied;
    }

    public BedAudit occupied(Integer occupied) {
        this.occupied = occupied;
        return this;
    }

    public void setOccupied(Integer occupied) {
        this.occupied = occupied;
    }

    public Integer getBlocked() {
        return blocked;
    }

    public BedAudit blocked(Integer blocked) {
        this.blocked = blocked;
        return this;
    }

    public void setBlocked(Integer blocked) {
        this.blocked = blocked;
    }

    public Integer getVacant() {
        return vacant;
    }

    public BedAudit vacant(Integer vacant) {
        this.vacant = vacant;
        return this;
    }

    public void setVacant(Integer vacant) {
        this.vacant = vacant;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public BedAudit createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public BedAudit updatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedByMsgId() {
        return updatedByMsgId;
    }

    public BedAudit updatedByMsgId(String updatedByMsgId) {
        this.updatedByMsgId = updatedByMsgId;
        return this;
    }

    public void setUpdatedByMsgId(String updatedByMsgId) {
        this.updatedByMsgId = updatedByMsgId;
    }

    public Instant getAuditedOn() {
        return auditedOn;
    }

    public BedAudit auditedOn(Instant auditedOn) {
        this.auditedOn = auditedOn;
        return this;
    }

    public void setAuditedOn(Instant auditedOn) {
        this.auditedOn = auditedOn;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BedAudit)) {
            return false;
        }
        return id != null && id.equals(((BedAudit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BedAudit{" +
            "id=" + getId() +
            ", hospitalId='" + getHospitalId() + "'" +
            ", bedId='" + getBedId() + "'" +
            ", type='" + getType() + "'" +
            ", capacity=" + getCapacity() +
            ", occupied=" + getOccupied() +
            ", blocked=" + getBlocked() +
            ", vacant=" + getVacant() +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            ", updatedByMsgId='" + getUpdatedByMsgId() + "'" +
            ", auditedOn='" + getAuditedOn() + "'" +
            "}";
    }
}
