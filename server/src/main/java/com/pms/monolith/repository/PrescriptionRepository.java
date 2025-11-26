package com.pms.monolith.repository;

import com.pms.monolith.entity.Prescription;
import com.pms.monolith.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    List<Prescription> findByCreator(User creator);

    List<Prescription> findByCreatedAtBetween(Date startDate, Date endDate);

    List<Prescription> findByCreatorAndCreatedAtBetween(User creator, Date startDate, Date endDate);
}
