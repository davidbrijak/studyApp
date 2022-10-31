package com.projekt.klinikaStudyBase.repository;

import com.projekt.klinikaStudyBase.data.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {

    UserRefreshToken findByToken(String token);

}
