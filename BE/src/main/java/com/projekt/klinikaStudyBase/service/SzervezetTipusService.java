package com.projekt.klinikaStudyBase.service;

import com.projekt.klinikaStudyBase.dto.SzervezetTipusDetailDto;

import java.util.List;

public interface SzervezetTipusService {

    SzervezetTipusDetailDto add(final SzervezetTipusDetailDto szervezetTipus);

    List<SzervezetTipusDetailDto> findAll();

    void delete(Long id);

    SzervezetTipusDetailDto find(Long id);
}
