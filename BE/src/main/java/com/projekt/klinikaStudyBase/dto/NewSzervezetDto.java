package com.projekt.klinikaStudyBase.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewSzervezetDto {

    private Long id;

    private String nev;

    private String cim;

    private String adoszam;

    private Long szervezetTipusAzonosito;
}

