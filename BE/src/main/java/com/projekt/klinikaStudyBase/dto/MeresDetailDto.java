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
public class MeresDetailDto {

    private Long id;
    private String feltoltesSzam;
    private String alanyNev;
    private String taj;
    private String vizsgaloOrvosNev;
    private String operator1IdNev;
    private String operator2IdNev;
    private String vizsgalatiMod;
    private boolean isFizetve;
    private String megjegyzes;
    private Date datum;
    private String vizsgalatAra;
}
