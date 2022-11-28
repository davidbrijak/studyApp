package com.projekt.klinikaStudyBase.service;

import com.projekt.klinikaStudyBase.config.JwtConfiguration;
import com.projekt.klinikaStudyBase.data.entity.User;
import com.projekt.klinikaStudyBase.data.entity.UserRefreshToken;
import com.projekt.klinikaStudyBase.dto.TokenDto;
import com.projekt.klinikaStudyBase.dto.UserTokensDto;
import com.projekt.klinikaStudyBase.exception.KlinkaStutyBaseException;
import com.projekt.klinikaStudyBase.repository.UserRefreshTokenRepository;
import org.springframework.core.io.ClassPathResource;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

@Service
public class TokenService {

    private byte[] encodedCertificate;

    private final JwtConfiguration jwtConfiguration;

    private final UserRefreshTokenRepository userRefreshTokenRepository;

    public static final String USER_EMAIL_CLAIMS_KEY = "userEmail";
    public static final String USER_ID_CLAIMS_KEY = "userId";
    public static final String TOKEN_EXPIRATION_CLAIMS_KEY = "tokenExpiration";

    public TokenService(
            final JwtConfiguration jwtConfiguration, final UserRefreshTokenRepository userRefreshTokenRepository) {
        this.jwtConfiguration = jwtConfiguration;
        this.userRefreshTokenRepository = userRefreshTokenRepository;
    }

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

    private String generateJSONWebToken(final User user) {
        final Date tokenExpiration = new Date(System.currentTimeMillis() + jwtConfiguration.getAccessTokenLifetime());
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
