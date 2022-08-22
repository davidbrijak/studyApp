package com.projekt.klinikaStudyBase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewMeresDto {
    private Long id;
    private String feltoltesSzam;
    private SzemelyDetailDto alany;
    private SzemelyDetailDto operator_1;
    private SzemelyDetailDto operator_2;
    private VizsgalatiModDetailDto vizsgalatiMod;
    private boolean isFizetve;
    private String megjegyzes;
    private Date datum;
    private Long studyAzonosito;
    private SzemelyDetailDto vizsgaloOrvos;
}
