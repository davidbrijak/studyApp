package com.projekt.klinikaStudyBase.dto;

import com.projekt.klinikaStudyBase.data.entity.Meres;
import com.projekt.klinikaStudyBase.data.entity.Szemely;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SzemelyMeresHelper{
	private Szemely szemely;
	private Meres meres;
	private String szerepkorHivatkozasiNev;
	private boolean isFizetveToSzemely;
	private boolean isFizetveBySzervezet;
}