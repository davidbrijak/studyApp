package com.projekt.klinikaStudyBase.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FizetesiPeriodusDetailDto {

    private Long id;
    /**
     * Fizetesi periodus napokban megadva
     */
    @NotNull
    private Integer napok;
    @NotBlank
    private String hivatkozasiNev;
    @NotBlank
    private String megnevezes;
}
