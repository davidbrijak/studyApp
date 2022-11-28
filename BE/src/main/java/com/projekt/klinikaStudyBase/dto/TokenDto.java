package com.projekt.klinikaStudyBase.dto;

import lombok.Data;

@Data
public class TokenDto {
    private String token;

    public TokenDto token(String token) {
        this.token = token;
        return this;
    }
}
