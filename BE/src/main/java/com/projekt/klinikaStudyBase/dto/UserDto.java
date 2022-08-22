package com.projekt.klinikaStudyBase.dto;

import com.projekt.klinikaStudyBase.data.entity.Jogosultsag;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String felhasznaloNev;
    private String jelszo;
    private Set<Jogosultsag> jogosultsag = new HashSet<>();
}
