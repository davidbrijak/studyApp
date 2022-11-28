package com.projekt.klinikaStudyBase.repository;

import com.projekt.klinikaStudyBase.data.entity.ApiToken;
import com.projekt.klinikaStudyBase.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiTokenRepository extends JpaRepository<ApiToken, Long> {

    List<ApiToken> findByUser(User user);

    boolean existsByUserAndName(User user, String tokenName);
}
