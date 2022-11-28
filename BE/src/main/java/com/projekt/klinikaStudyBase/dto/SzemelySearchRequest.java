package com.projekt.klinikaStudyBase.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SzemelySearchRequest {

	private String azonositoSearch;
	private String nevSearch;
	private Long szerepekor;
}
