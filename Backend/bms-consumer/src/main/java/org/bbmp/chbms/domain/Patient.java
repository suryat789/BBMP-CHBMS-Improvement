package org.bbmp.chbms.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Patient.
 */
@Entity
@Table(name = "patient")
public class Patient implements Serializable {

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

    public Patient patientId(String patientId) {
        this.patientId = patientId;
        return this;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPhone() {
        return phone;
    }

    public Patient phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSrfNumber() {
        return srfNumber;
    }

    public Patient srfNumber(String srfNumber) {
        this.srfNumber = srfNumber;
        return this;
    }

    public void setSrfNumber(String srfNumber) {
        this.srfNumber = srfNumber;
    }

    public String getBucode() {
        return bucode;
    }

    public Patient bucode(String bucode) {
        this.bucode = bucode;
        return this;
    }

    public void setBucode(String bucode) {
        this.bucode = bucode;
    }

    public String getCategory() {
        return category;
    }

    public Patient category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getZone() {
        return zone;
    }

    public Patient zone(String zone) {
        this.zone = zone;
        return this;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getQueueType() {
        return queueType;
    }

    public Patient queueType(String queueType) {
        this.queueType = queueType;
        return this;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getQueueName() {
        return queueName;
    }

    public Patient queueName(String queueName) {
        this.queueName = queueName;
        return this;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Instant getTimeAddedToQueue() {
        return timeAddedToQueue;
    }

    public Patient timeAddedToQueue(Instant timeAddedToQueue) {
        this.timeAddedToQueue = timeAddedToQueue;
        return this;
    }

    public void setTimeAddedToQueue(Instant timeAddedToQueue) {
        this.timeAddedToQueue = timeAddedToQueue;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public Patient createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public Patient updatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedByMsgId() {
        return updatedByMsgId;
    }

    public Patient updatedByMsgId(String updatedByMsgId) {
        this.updatedByMsgId = updatedByMsgId;
        return this;
    }

    public void setUpdatedByMsgId(String updatedByMsgId) {
        this.updatedByMsgId = updatedByMsgId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Patient)) {
            return false;
        }
        return id != null && id.equals(((Patient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Patient{" +
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
            "}";
    }
}
