package com.projekt.klinikaStudyBase.repository;

import com.projekt.klinikaStudyBase.data.entity.SzervezetTipus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SzervezetTipusRepository extends JpaRepository<SzervezetTipus,Long>, JpaSpecificationExecutor<SzervezetTipus> {
    SzervezetTipus findSzervezetTipusByMegnevezes(String hivatkozasiNev);
}
