package com.projekt.klinikaStudyBase.repository;

import com.projekt.klinikaStudyBase.data.entity.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
}
