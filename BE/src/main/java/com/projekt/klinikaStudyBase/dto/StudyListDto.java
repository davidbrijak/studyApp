package com.projekt.klinikaStudyBase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudyListDto {

	private Long id;
	private String cim;
	private String studyKod;
}
