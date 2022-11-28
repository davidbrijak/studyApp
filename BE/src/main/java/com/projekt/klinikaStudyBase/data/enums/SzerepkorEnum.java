package com.projekt.klinikaStudyBase.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum SzerepkorEnum {
	VIZSGALO_ORVOS("vizsgáló orvos"),
	OPERATOR_1("operátor 1"),
	OPERATOR_2("operátor 2"),
	MINITOR("monitor");

	private String megnevezes;
}
