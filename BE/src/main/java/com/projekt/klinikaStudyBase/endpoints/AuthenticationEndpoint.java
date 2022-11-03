package com.projekt.klinikaStudyBase.endpoints;

import com.projekt.klinikaStudyBase.dto.UserCredentialsDto;
import com.projekt.klinikaStudyBase.dto.UserTokensDto;
import com.projekt.klinikaStudyBase.exception.KlinkaStutyBaseException;
import com.projekt.klinikaStudyBase.service.TokenService;
import com.projekt.klinikaStudyBase.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationEndpoint {
    private final UserService userService;
    private final TokenService tokenService;

    public AuthenticationEndpoint(final UserService userService, final TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
    @Transactional
    public ResponseEntity<UserTokensDto> login(@RequestBody final UserCredentialsDto userCredentialsDto) {
        return Optional.ofNullable(userCredentialsDto).map(userService::getValidatedUser)
                .map(tokenService::getTokens).map(ResponseEntity::ok)
                .orElseThrow(() -> new KlinkaStutyBaseException("Invalid credentials", HttpStatus.UNAUTHORIZED));
    }
}
