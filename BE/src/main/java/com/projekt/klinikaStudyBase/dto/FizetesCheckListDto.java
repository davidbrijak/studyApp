package com.projekt.klinikaStudyBase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FizetesCheckListDto {

    private Long id;
    private Long szemelyId;
    private String szemely;
    private String szerepkor;
    private String osszeg;
    private boolean fizetveBySzervezet;
    private boolean fizetveToSzemely;
    private String meresFeltoltesSzam;
    private Long meresId;
}
