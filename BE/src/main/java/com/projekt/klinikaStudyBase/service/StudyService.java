package com.projekt.klinikaStudyBase.service;

import com.projekt.klinikaStudyBase.dto.NewStudyDto;
import com.projekt.klinikaStudyBase.dto.StudyDetailDto;
import com.projekt.klinikaStudyBase.dto.StudyListDto;

import java.util.List;

public interface StudyService {

    void add(NewStudyDto newStudyDto);

    StudyDetailDto findById(Long id);

    List<StudyListDto> findAll();
}
