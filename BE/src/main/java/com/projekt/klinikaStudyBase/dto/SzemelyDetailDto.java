package com.projekt.klinikaStudyBase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SzemelyDetailDto {

    private Long id;
    private String nev;
    private String telefonSzam;
    private String postaCim;
    private String email;
    private String tajszam;
    private Set<SzerepkorDetailDto> szerepkorok = new HashSet<>();
}
