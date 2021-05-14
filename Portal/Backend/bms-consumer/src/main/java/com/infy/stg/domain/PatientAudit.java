package com.infy.stg.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A PatientAudit.
 */
@Entity
@Table(name = "patient_audit")
public class PatientAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "phone")
    private String phone;

    @Column(name = "srf_number")
    private String srfNumber;

    @Column(name = "bucode")
    private String bucode;

    @Column(name = "category")
    private String category;

    @Column(name = "zone")
    private String zone;

    @Column(name = "queue_type")
    private String queueType;

    @Column(name = "queue_name")
    private String queueName;

    @Column(name = "time_added_to_queue")
    private Instant timeAddedToQueue;

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

    public String getPatientId() {
        return patientId;
    }

    public PatientAudit patientId(String patientId) {
        this.patientId = patientId;
        return this;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPhone() {
        return phone;
    }

    public PatientAudit phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSrfNumber() {
        return srfNumber;
    }

    public PatientAudit srfNumber(String srfNumber) {
        this.srfNumber = srfNumber;
        return this;
    }

    public void setSrfNumber(String srfNumber) {
        this.srfNumber = srfNumber;
    }

    public String getBucode() {
        return bucode;
    }

    public PatientAudit bucode(String bucode) {
        this.bucode = bucode;
        return this;
    }

    public void setBucode(String bucode) {
        this.bucode = bucode;
    }

    public String getCategory() {
        return category;
    }

    public PatientAudit category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getZone() {
        return zone;
    }

    public PatientAudit zone(String zone) {
        this.zone = zone;
        return this;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getQueueType() {
        return queueType;
    }

    public PatientAudit queueType(String queueType) {
        this.queueType = queueType;
        return this;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getQueueName() {
        return queueName;
    }

    public PatientAudit queueName(String queueName) {
        this.queueName = queueName;
        return this;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Instant getTimeAddedToQueue() {
        return timeAddedToQueue;
    }

    public PatientAudit timeAddedToQueue(Instant timeAddedToQueue) {
        this.timeAddedToQueue = timeAddedToQueue;
        return this;
    }

    public void setTimeAddedToQueue(Instant timeAddedToQueue) {
        this.timeAddedToQueue = timeAddedToQueue;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public PatientAudit createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public PatientAudit updatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedByMsgId() {
        return updatedByMsgId;
    }

    public PatientAudit updatedByMsgId(String updatedByMsgId) {
        this.updatedByMsgId = updatedByMsgId;
        return this;
    }

    public void setUpdatedByMsgId(String updatedByMsgId) {
        this.updatedByMsgId = updatedByMsgId;
    }

    public Instant getAuditedOn() {
        return auditedOn;
    }

    public PatientAudit auditedOn(Instant auditedOn) {
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
        if (!(o instanceof PatientAudit)) {
            return false;
        }
        return id != null && id.equals(((PatientAudit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientAudit{" +
            "id=" + getId() +
            ", patientId='" + getPatientId() + "'" +
            ", phone='" + getPhone() + "'" +
            ", srfNumber='" + getSrfNumber() + "'" +
            ", bucode='" + getBucode() + "'" +
            ", category='" + getCategory() + "'" +
            ", zone='" + getZone() + "'" +
            ", queueType='" + getQueueType() + "'" +
            ", queueName='" + getQueueName() + "'" +
            ", timeAddedToQueue='" + getTimeAddedToQueue() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            ", updatedByMsgId='" + getUpdatedByMsgId() + "'" +
            ", auditedOn='" + getAuditedOn() + "'" +
            "}";
    }
}
