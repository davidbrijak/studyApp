package com.projekt.klinikaStudyBase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class KifizetesChangedList {
	List<KifizetesChanged> kifizetesChangedList;
}
