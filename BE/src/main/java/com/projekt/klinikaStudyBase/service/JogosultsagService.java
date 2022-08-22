package com.projekt.klinikaStudyBase.service;

import com.projekt.klinikaStudyBase.dto.JogosultsagDetailDto;

import java.util.List;

public interface JogosultsagService {

    JogosultsagDetailDto addNewJogosultsag(JogosultsagDetailDto newJogosultsagDto);

    List<JogosultsagDetailDto> findAll();

    JogosultsagDetailDto find(Long id);
}
