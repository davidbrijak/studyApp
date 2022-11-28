package com.projekt.klinikaStudyBase.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewStudyDto {

    private Long id;
    private String cim;
    private String studyKod;
    private SzervezetDetailDto megbizoCeg;
    private SzervezetDetailDto cro;
    private SzemelyDetailDto kutatasVezeto;
    private SzervezetDetailDto reszleg;
    private SzemelyDetailDto monitor;
    private SzemelyDetailDto penzugyiKontakt;
    //private Long szamlazasiCim; megbizo ceg cime
    private Date szerzodesKotesDatuma;
    private FizetesiPeriodusDetailDto fizetesiPeriodusDetailDto;
}
