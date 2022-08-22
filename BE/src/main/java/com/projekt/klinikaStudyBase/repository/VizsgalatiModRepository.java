package com.projekt.klinikaStudyBase.repository;

import com.projekt.klinikaStudyBase.data.entity.VizsgalatiMod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VizsgalatiModRepository extends JpaRepository<VizsgalatiMod, Long>, JpaSpecificationExecutor<VizsgalatiMod> {
}
