package com.chbms.queuemgmt.data.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "QUEUE")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QueueEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter
    @Column(name = "TYPE")
    String type;

    @Column(name = "PATIENT_ID")
    Long patientId;

    @Setter
    @Column(name = "ENQUEUE_TIMESTAMP")
    Instant enqueueTimestamp;

    @Column(name = "UPDATED_TIMESTAMP")
    Instant updatedTimestamp;

    @Setter
    @Column(name = "IS_ACTIVE")
    Boolean isActive = true;

    @Setter
    @Column(name = "ZONE")
    String zone;

    @Setter
    @Column(name = "PUSH_FRONT")
    Integer pushFront = 0;

    @PreUpdate
    public void preUpdate() {
        this.updatedTimestamp = Instant.now();
    }
}
