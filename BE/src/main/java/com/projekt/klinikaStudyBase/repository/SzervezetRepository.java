package com.projekt.klinikaStudyBase.repository;

import com.projekt.klinikaStudyBase.data.entity.Szervezet;
import com.projekt.klinikaStudyBase.data.entity.SzervezetTipus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SzervezetRepository extends JpaRepository<Szervezet,Long>, JpaSpecificationExecutor<Szervezet> {

    List<Szervezet> findAllBySzervezetTipus(SzervezetTipus szervezetTipus);
}
