package com.chbms.queuemgmt.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "ALLOTMENT")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Allotment {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "QUEUE_ID")
    Long queueId;

    @Column(name = "PATIENT_ID")
    Long patientId;

    @Column(name = "HOSPITAL_ID")
    String hospitalId;

    @Column(name = "BED_TYPE")
    String bedType;

    @Column(name = "REQUEST_TIME")
    Instant requestTime;

    @Column(name = "ALLOCATION_TIME")
    Instant allocationTime;

    @Column(name = "ZONE")
    String zone;

    @PrePersist
    public void prePersist() {
        this.allocationTime = Instant.now();
    }
}
