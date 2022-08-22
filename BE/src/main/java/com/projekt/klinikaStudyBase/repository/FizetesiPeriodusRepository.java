package com.projekt.klinikaStudyBase.repository;

import com.projekt.klinikaStudyBase.data.entity.FizetesiPeriodus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FizetesiPeriodusRepository extends JpaRepository<FizetesiPeriodus, Long>, JpaSpecificationExecutor<FizetesiPeriodus> {
}
