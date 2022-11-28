package com.projekt.klinikaStudyBase.dto;

import com.projekt.klinikaStudyBase.data.entity.Szerepkor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link Szerepkor} entitashoz tartozo dto
 *
 * @author brijakd
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SzerepkorDetailDto {

    private Long id;
    private String hivatkozasiNev;
    private String megnevezes;
    private Integer kifizetesAranya;
}
