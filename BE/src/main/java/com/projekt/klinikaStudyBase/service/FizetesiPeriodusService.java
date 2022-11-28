package com.projekt.klinikaStudyBase.service;

import com.projekt.klinikaStudyBase.dto.FizetesiPeriodusDetailDto;

import java.util.List;

public interface FizetesiPeriodusService {

    FizetesiPeriodusDetailDto add(FizetesiPeriodusDetailDto newFizetesiperiodusDetailDto);

    FizetesiPeriodusDetailDto find(Long id);

    List<FizetesiPeriodusDetailDto> findAll();

    void delete(Long id);
}
