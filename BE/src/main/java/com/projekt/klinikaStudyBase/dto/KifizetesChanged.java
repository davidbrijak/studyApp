package com.projekt.klinikaStudyBase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class KifizetesChanged {
	private Long meresId;
	private boolean isFizetveBySzervezet;
	private boolean isFizetveToSzemely;
	private String szerepkorHivatkozasiNev;

}
