package com.projekt.klinikaStudyBase.repository;

import com.projekt.klinikaStudyBase.data.entity.Meres;
import com.projekt.klinikaStudyBase.data.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeresRepository extends JpaRepository<Meres, Long>, JpaSpecificationExecutor<Meres> {
    List<Meres> findAllByStudy(Study study);
}
