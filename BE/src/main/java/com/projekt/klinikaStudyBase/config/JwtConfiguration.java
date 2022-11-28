package com.projekt.klinikaStudyBase.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "klinika.study.base.jwt")
public class JwtConfiguration {

    private String certificationFile;
    private String issuer;
    private Long accessTokenLifetime;

    public String getCertificationFile() {
        return certificationFile;
    }

    public void setCertificationFile(String certificationFile) {
        this.certificationFile = certificationFile;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Long getAccessTokenLifetime() {
        return accessTokenLifetime;
    }

    public void setAccessTokenLifetime(Long accessTokenLifetime) {
        this.accessTokenLifetime = accessTokenLifetime;
    }
}
