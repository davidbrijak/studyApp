package com.projekt.klinikaStudyBase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SzervezetDetailDto {

    private Long id;

    private String nev;

    private String cim;

    private String adoszam;

    private String szervezetTipusNev;
}
