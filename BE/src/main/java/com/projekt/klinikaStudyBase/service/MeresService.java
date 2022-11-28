package com.projekt.klinikaStudyBase.service;

import com.projekt.klinikaStudyBase.dto.MeresDetailDto;
import com.projekt.klinikaStudyBase.dto.MeresFilterDto;
import com.projekt.klinikaStudyBase.dto.NewMeresDto;

import java.util.List;

public interface MeresService {

    NewMeresDto add(NewMeresDto newMeresDto);

    List<MeresDetailDto> findAll();

    NewMeresDto findById(Long id);

    List<MeresDetailDto> findByFilter(MeresFilterDto meresFilterDto);

    List<MeresDetailDto> findStudy(Long studyAzonosito);
}
