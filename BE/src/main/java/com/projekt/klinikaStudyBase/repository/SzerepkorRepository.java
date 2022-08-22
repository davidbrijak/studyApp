package com.projekt.klinikaStudyBase.repository;

import com.projekt.klinikaStudyBase.data.entity.Szerepkor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SzerepkorRepository extends JpaRepository<Szerepkor,Long>, JpaSpecificationExecutor<Szerepkor> {

	Optional<Szerepkor> findSzerepkorByHivatkozasiNev(String hivNev);
}
