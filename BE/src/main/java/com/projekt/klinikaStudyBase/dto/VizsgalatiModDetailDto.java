package com.projekt.klinikaStudyBase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VizsgalatiModDetailDto {
    private Long id;
    private String hivatkozasiNev;
    private String megnevezes;
    private Double eur;
    private Double huf;
    //private String megjegyzes; TODO
}
