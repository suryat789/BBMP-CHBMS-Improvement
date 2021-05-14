package com.infy.stg.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Hospital.
 */
@Entity
@Table(name = "hospital")
public class Hospital implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hospital_id")
    private String hospitalId;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "pincode")
    private Long pincode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "zone")
    private String zone;

    @Column(name = "status")
    private String status;

    @Column(name = "created_on")
    private Instant createdOn;

    @Column(name = "updated_on")
    private Instant updatedOn;

    @Column(name = "updated_by_msg_id")
    private String updatedByMsgId;

    @OneToMany(mappedBy = "hospital")
    private Set<Bed> ids = new HashSet<>();

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

    public Hospital hospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
        return this;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getType() {
        return type;
    }

    public Hospital type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Hospital name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public Hospital address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPincode() {
        return pincode;
    }

    public Hospital pincode(Long pincode) {
        this.pincode = pincode;
        return this;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getPhone() {
        return phone;
    }

    public Hospital phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZone() {
        return zone;
    }

    public Hospital zone(String zone) {
        this.zone = zone;
        return this;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getStatus() {
        return status;
    }

    public Hospital status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public Hospital createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public Hospital updatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedByMsgId() {
        return updatedByMsgId;
    }

    public Hospital updatedByMsgId(String updatedByMsgId) {
        this.updatedByMsgId = updatedByMsgId;
        return this;
    }

    public void setUpdatedByMsgId(String updatedByMsgId) {
        this.updatedByMsgId = updatedByMsgId;
    }

    public Set<Bed> getIds() {
        return ids;
    }

    public Hospital ids(Set<Bed> beds) {
        this.ids = beds;
        return this;
    }

    public Hospital addId(Bed bed) {
        this.ids.add(bed);
        bed.setHospital(this);
        return this;
    }

    public Hospital removeId(Bed bed) {
        this.ids.remove(bed);
        bed.setHospital(null);
        return this;
    }

    public void setIds(Set<Bed> beds) {
        this.ids = beds;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Hospital)) {
            return false;
        }
        return id != null && id.equals(((Hospital) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Hospital{" +
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
            "}";
    }
}
