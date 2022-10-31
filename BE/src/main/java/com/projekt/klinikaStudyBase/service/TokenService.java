package com.projekt.klinikaStudyBase.service;

import com.projekt.klinikaStudyBase.config.JwtConfiguration;
import com.projekt.klinikaStudyBase.data.entity.ApiToken;
import com.projekt.klinikaStudyBase.data.entity.User;
import com.projekt.klinikaStudyBase.data.entity.UserRefreshToken;
import com.projekt.klinikaStudyBase.dto.TokenDto;
import com.projekt.klinikaStudyBase.dto.UserTokensDto;
import com.projekt.klinikaStudyBase.exception.KlinkaStutyBaseException;
import com.projekt.klinikaStudyBase.repository.ApiTokenRepository;
import com.projekt.klinikaStudyBase.repository.UserRefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.*;

@Service
public class TokenService {

    private byte[] encodedCertificate;

    @Autowired
    private JwtConfiguration jwtConfiguration;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApiTokenRepository apiTokenRepository;

    @Autowired
    private UserRefreshTokenRepository userRefreshTokenRepository;

    public static final String USER_EMAIL_CLAIMS_KEY = "userEmail";
    public static final String USER_ID_CLAIMS_KEY = "userId";
    public static final String TOKEN_EXPIRATION_CLAIMS_KEY = "tokenExpiration";

    @PostConstruct
    private void getEncodedCertificate() throws KlinkaStutyBaseException {
        byte[] certificateResource = getCertificateResource();
        String cert = new String(certificateResource, StandardCharsets.UTF_8);
        encodedCertificate =
                cert.replaceAll("-----(BEGIN|END) CERTIFICATE-----", "").getBytes(StandardCharsets.UTF_8);
    }

    private byte[] getCertificateResource() throws KlinkaStutyBaseException {
        try {
            return new ClassPathResource(jwtConfiguration.getCertificationFile()).getInputStream()
                    .readAllBytes();
        } catch (IOException e) {
            throw new KlinkaStutyBaseException("Failed to load certificate");
        }
    }

    @Transactional
    public ApiToken createApiToken(User user, String tokenName) throws KlinkaStutyBaseException {
        if (apiTokenRepository.existsByUserAndName(user, tokenName)) {
            throw new KlinkaStutyBaseException("Token name already in use", HttpStatus.BAD_REQUEST);
        }
        String rawApiToken = UUID.randomUUID().toString();
        ApiToken saved = apiTokenRepository.save(
                new ApiToken(user, passwordEncoder.encode(rawApiToken), tokenName, OffsetDateTime.now()));
        return getNewForResponse(rawApiToken, saved);
    }

    private ApiToken getNewForResponse(String rawApiToken, ApiToken saved) {
        ApiToken newWithRawToken = new ApiToken();
        newWithRawToken.setToken(rawApiToken);
        newWithRawToken.setId(saved.getId());
        return newWithRawToken;
    }

    public UserTokensDto getTokens(User user) {
        String refreshToken = createRefreshToken(user);
        String accessToken = generateJSONWebToken(user);
        return createUserTokensDto(accessToken, refreshToken);
    }

    private String createRefreshToken(User user) {
        return userRefreshTokenRepository.save(new UserRefreshToken(user)).getToken();
    }

    private UserTokensDto createUserTokensDto(String accessToken, String refreshToken) {
        UserTokensDto userTokensDto = new UserTokensDto();
        userTokensDto.setAccessToken(new TokenDto().token(accessToken));
        userTokensDto.setRefreshToken(new TokenDto().token(refreshToken));
        return userTokensDto;
    }

    public String refreshAccessToken(TokenDto userRefreshTokenDto) throws KlinkaStutyBaseException {
        return Optional.ofNullable(userRefreshTokenDto).map(TokenDto::getToken)
                .map(userRefreshTokenRepository::findByToken).filter(UserRefreshToken::isValid)
                .map(UserRefreshToken::getUser).map(this::generateJSONWebToken)
                .orElseThrow(() -> new KlinkaStutyBaseException("Invalid token", HttpStatus.UNAUTHORIZED));
    }

    private String generateJSONWebToken(User user) {
        Date tokenExpiration =
                new Date(System.currentTimeMillis() + jwtConfiguration.getAccessTokenLifetime());
        return Jwts.builder().setClaims(buildClaims(user, tokenExpiration)).setSubject(user.getEmail())
                .setIssuedAt(new Date()).setExpiration(tokenExpiration)
                .setIssuer(jwtConfiguration.getIssuer()).signWith(Keys.hmacShaKeyFor(encodedCertificate))
                .compact();
    }

    private Map<String, Object> buildClaims(User user, Date tokenExpiration) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(USER_EMAIL_CLAIMS_KEY, user.getEmail());
        claims.put(USER_ID_CLAIMS_KEY, user.getId());
        claims.put(TOKEN_EXPIRATION_CLAIMS_KEY, tokenExpiration);
        return claims;
    }
}
