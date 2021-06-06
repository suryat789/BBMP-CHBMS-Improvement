package com.chbms.queuemgmt.data.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PATIENT")
@Data
@ToString
public class Patient {

    @Id
    @Column(name = "PATIENT_ID")
    Long patientId;

    @Column(name = "BU_NUMBER")
    Long buNumber;

    @Column(name = "CONTACT_NUMBER")
    String contactNumber;

    @Column(name = "ICMR_NUMBER")
    String icmrNumber;

    @Column(name = "SRF_NUMBER")
    String srfNumber;

    @Column(name = "ZONE")
    String zone;

}
