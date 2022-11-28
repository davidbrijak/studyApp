package com.projekt.klinikaStudyBase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewSzervezetDto {

    private Long id;

    private String nev;

    private String cim;

    private String adoszam;

    private Long szervezetTipusAzonosito;
}

