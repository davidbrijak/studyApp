package com.projekt.klinikaStudyBase.dto;

import lombok.Data;

@Data
public class UserTokensDto {

    private TokenDto accessToken;
    private TokenDto refreshToken;
}
