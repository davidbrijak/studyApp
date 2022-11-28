package com.projekt.klinikaStudyBase.service;

import com.projekt.klinikaStudyBase.dto.NewSzervezetDto;
import com.projekt.klinikaStudyBase.dto.SzervezetDetailDto;
import java.util.List;

public interface SzervezetService {

    SzervezetDetailDto add(NewSzervezetDto newSzervezetDto);

    List<SzervezetDetailDto> findAll();

    List<SzervezetDetailDto> findAllByTipus(Long szervezetTipusId);

    void delete(Long id);
}
