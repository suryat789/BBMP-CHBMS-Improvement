package com.chbms.queuemgmt.data.repository;

import com.chbms.queuemgmt.data.entity.Allotment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllotmentRepository extends JpaRepository<Allotment, Long> {
}
