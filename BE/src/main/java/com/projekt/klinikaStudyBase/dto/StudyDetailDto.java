package com.projekt.klinikaStudyBase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudyDetailDto {

    private Long id;
    private String cim;
    private String studyKod;
    private SzervezetDetailDto cro;
    private SzervezetDetailDto megbizoCeg;
    private SzemelyDetailDto kutatasVezeto;
    private SzervezetDetailDto reszleg;
    private SzemelyDetailDto monitor;
    private SzemelyDetailDto penzugyiKontakt;
    private Date szerzodeskotesDatum;
    private FizetesiPeriodusDetailDto fizetesiPeriodusDetailDto;
    private List<MeresDetailDto> meresek = new ArrayList<>();
}
