package com.projekt.klinikaStudyBase.service.impl;

import com.projekt.klinikaStudyBase.data.entity.Jogosultsag;
import com.projekt.klinikaStudyBase.dto.JogosultsagDetailDto;
import com.projekt.klinikaStudyBase.exception.FizetesiModNotFoundException;
import com.projekt.klinikaStudyBase.helper.mapper.JogosultsagMapper;
import com.projekt.klinikaStudyBase.repository.JogosultsagRepository;
import com.projekt.klinikaStudyBase.service.JogosultsagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class JogosultsagServiceImpl implements JogosultsagService {

    @Autowired
    private JogosultsagRepository jogosultsagRepository;

    @Autowired
    private JogosultsagMapper jogosultsagMapper;

    @Override
    public JogosultsagDetailDto addNewJogosultsag(final JogosultsagDetailDto jogosultsagDetailDto) {
        Jogosultsag jogosultsagForSave = jogosultsagMapper.mapToJogosultsagEntity(jogosultsagDetailDto);
        Jogosultsag savedJogosultsag = jogosultsagRepository.save(jogosultsagForSave);
        return jogosultsagMapper.mapToDto(savedJogosultsag);
    }

    @Override
    public List<JogosultsagDetailDto> findAll() {
        final var foundEntityList = jogosultsagRepository.findAll();
        return jogosultsagMapper.mapToDtoList(foundEntityList);
    }

    @Override
    public JogosultsagDetailDto find(final Long id) {
        final var foundEntity = jogosultsagRepository.findById(id)
                .orElseThrow(
                        () -> new FizetesiModNotFoundException(id.toString())
                );

        return jogosultsagMapper.mapToDto(foundEntity);
    }
}
