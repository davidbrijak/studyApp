package com.projekt.klinikaStudyBase.service;

import com.projekt.klinikaStudyBase.dto.VizsgalatiModDetailDto;

import java.util.List;

public interface VizsgalatiModService {

    VizsgalatiModDetailDto add(VizsgalatiModDetailDto newVizsgalatiMod);

    List<VizsgalatiModDetailDto> findAll();

    VizsgalatiModDetailDto findById(Long id);

    void deleteById(Long id);
}
