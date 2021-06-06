package org.bbmp.chbms.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link org.bbmp.chbms.domain.Bed} entity.
 */
public class BedDTO implements Serializable {
    
    private Long id;

    private String bedId;

    private String type;

    private Integer capacity;

    private Integer occupied;

    private Integer blocked;

    private Integer vacant;

    private Instant createdOn;

    private Instant updatedOn;

    private String updatedByMsgId;


    private Long hospitalId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBedId() {
        return bedId;
    }

    public void setBedId(String bedId) {
        this.bedId = bedId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getOccupied() {
        return occupied;
    }

    public void setOccupied(Integer occupied) {
        this.occupied = occupied;
    }

    public Integer getBlocked() {
        return blocked;
    }

    public void setBlocked(Integer blocked) {
        this.blocked = blocked;
    }

    public Integer getVacant() {
        return vacant;
    }

    public void setVacant(Integer vacant) {
        this.vacant = vacant;
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

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BedDTO)) {
            return false;
        }

        return id != null && id.equals(((BedDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BedDTO{" +
            "id=" + getId() +
            ", bedId='" + getBedId() + "'" +
            ", type='" + getType() + "'" +
            ", capacity=" + getCapacity() +
            ", occupied=" + getOccupied() +
            ", blocked=" + getBlocked() +
            ", vacant=" + getVacant() +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            ", updatedByMsgId='" + getUpdatedByMsgId() + "'" +
            ", hospitalId=" + getHospitalId() +
            "}";
    }
}
