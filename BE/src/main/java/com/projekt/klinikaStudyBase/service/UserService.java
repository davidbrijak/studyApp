package com.projekt.klinikaStudyBase.service;

import com.projekt.klinikaStudyBase.config.JwtConfiguration;
import com.projekt.klinikaStudyBase.data.entity.User;
import com.projekt.klinikaStudyBase.dto.UserCredentialsDto;
import com.projekt.klinikaStudyBase.exception.KlinkaStutyBaseException;
import com.projekt.klinikaStudyBase.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfiguration jwtConfiguration;

    public UserService(
            final UserRepository userRepository, final PasswordEncoder passwordEncoder,
            final JwtConfiguration jwtConfiguration) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtConfiguration = jwtConfiguration;
    }

    public User getValidatedUser(UserCredentialsDto userCredentialsDto) {
        User user = userRepository.findUserByEmail(userCredentialsDto.getEmail()).orElseThrow(() -> {
            throw new KlinkaStutyBaseException("User with given email could not be found.",
                    HttpStatus.UNAUTHORIZED);
        });
        return passwordEncoder.matches(userCredentialsDto.getPassword(), user.getJelszo())
                ? userRepository.findUserByEmail(userCredentialsDto.getEmail()).orElse(null)
                : null;
    }
}
