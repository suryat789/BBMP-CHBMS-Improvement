package com.infy.stg.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.infy.stg.domain.Patient} entity.
 */
public class PatientDTO implements Serializable {
    
    private Long id;

    private String patientId;

    private String phone;

    private String srfNumber;

    private String bucode;

    private String category;

    private String zone;

    private String queueType;

    private String queueName;

    private Instant timeAddedToQueue;

    private Instant createdOn;

    private Instant updatedOn;

    private String updatedByMsgId;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSrfNumber() {
        return srfNumber;
    }

    public void setSrfNumber(String srfNumber) {
        this.srfNumber = srfNumber;
    }

    public String getBucode() {
        return bucode;
    }

    public void setBucode(String bucode) {
        this.bucode = bucode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Instant getTimeAddedToQueue() {
        return timeAddedToQueue;
    }

    public void setTimeAddedToQueue(Instant timeAddedToQueue) {
        this.timeAddedToQueue = timeAddedToQueue;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientDTO)) {
            return false;
        }

        return id != null && id.equals(((PatientDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientDTO{" +
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
