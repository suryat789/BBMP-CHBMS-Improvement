package com.chbms.queuemgmt.data.repository;

import com.chbms.queuemgmt.data.entity.QueueEntry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface QueueRepository extends JpaRepository<QueueEntry, Long> {

    Optional<QueueEntry> findByPatientIdAndIsActiveAndType(Long patientId, boolean active, String type);

    List<QueueEntry> findByPatientIdAndIsActive(Long patientId, boolean isActive);

    @Query("SELECT q from QueueEntry q where q.type in (?1) and q.zone=?2 and q.isActive=true order by q.pushFront, q.enqueueTimestamp")
    List<QueueEntry> getEntriesForTypesInAZone(List<String> types, String zone, Pageable pageable);

    @Query("SELECT q from QueueEntry q where q.type in (?1) and q.isActive=true order by q.pushFront, q.enqueueTimestamp")
    List<QueueEntry> getEntriesForTypes(List<String> types, Pageable pageable);

    @Query(value = "Select count(*) from QueueEntry where type = :queueType and pushFront = 0 and enqueueTimestamp < ( SELECT enqueueTimestamp from QueueEntry where patientId = :patientId and type = :queueType  and isActive=true and pushFront = 0) and isActive=true")
    Long findRankByQueueTypeUnPushed(@Param("patientId") final Long patientId, @Param("queueType") String queueType);

    @Query(value = "Select count(*) from QueueEntry where type = :queueType and pushFront = -1 and enqueueTimestamp < ( SELECT enqueueTimestamp from QueueEntry where patientId = :patientId and type = :queueType  and isActive=true and pushFront = -1) and isActive=true")
    Long findRankByQueueTypePushed(@Param("patientId") final Long patientId, @Param("queueType") String queueType);

    @Query(value = "Select count(*) from QueueEntry where type = :queueType and isActive = true and pushFront = -1")
    Long findPushedCountByQueueType(@Param("queueType") String queueType);

}
