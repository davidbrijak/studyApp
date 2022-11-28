package com.projekt.klinikaStudyBase.repository;

import com.projekt.klinikaStudyBase.data.entity.Jogosultsag;
import com.projekt.klinikaStudyBase.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface JogosultsagRepository extends JpaRepository<Jogosultsag, Long>, JpaSpecificationExecutor<Jogosultsag> {
    Set<Jogosultsag> findAllByFelhasznalok(User user);
}
