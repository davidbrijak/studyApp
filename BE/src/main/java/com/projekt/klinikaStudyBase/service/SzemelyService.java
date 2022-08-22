package com.projekt.klinikaStudyBase.service;

import com.projekt.klinikaStudyBase.dto.NewSzemelyDto;
import com.projekt.klinikaStudyBase.dto.SzemelyDetailDto;

import java.util.List;

public interface SzemelyService {

    SzemelyDetailDto addSzemelyWithSzerepkor(NewSzemelyDto newSzemelyDto);

    SzemelyDetailDto findbyId(Long id);

    List<SzemelyDetailDto> findAll();
}
