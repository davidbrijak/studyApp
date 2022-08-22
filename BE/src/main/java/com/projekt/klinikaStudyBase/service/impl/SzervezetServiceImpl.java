package com.projekt.klinikaStudyBase.service.impl;

import com.projekt.klinikaStudyBase.data.entity.Szervezet;
import com.projekt.klinikaStudyBase.data.entity.SzervezetTipus;
import com.projekt.klinikaStudyBase.dto.NewSzervezetDto;
import com.projekt.klinikaStudyBase.dto.SzervezetDetailDto;
import com.projekt.klinikaStudyBase.helper.mapper.SzervezetMapper;
import com.projekt.klinikaStudyBase.repository.SzervezetRepository;
import com.projekt.klinikaStudyBase.repository.SzervezetTipusRepository;
import com.projekt.klinikaStudyBase.service.SzervezetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SzervezetServiceImpl implements SzervezetService {

    private final SzervezetTipusRepository szervezetTipusRepository;
    private final SzervezetRepository szervezetRepository;
    private final SzervezetMapper szervezetMapper;

    @Override
    public SzervezetDetailDto add(final NewSzervezetDto newSzervezetDto) {
        final var szervezet = szervezetMapper.toEntity(newSzervezetDto);
        final var savedSzervezet = szervezetRepository.save(szervezet);
        return szervezetMapper.toDto(savedSzervezet);
    }

    @Override
    public List<SzervezetDetailDto> findAll() {
        List<Szervezet> szervezets = szervezetRepository.findAll();
        return szervezetMapper.toListDto(szervezets);
    }

    @Override
    public List<SzervezetDetailDto> findAllByTipus(final Long szervezetTipusId) {
        List<Szervezet> szervezetekByTipus = new ArrayList<>();
        SzervezetTipus szervezetTipus = szervezetTipusRepository.findById(szervezetTipusId).orElse(null);
        if (Objects.nonNull(szervezetTipus)) {
            szervezetekByTipus = szervezetRepository.findAllBySzervezetTipus(szervezetTipus);
        }
        return szervezetMapper.toListDto(szervezetekByTipus);
    }

    @Override
    public void delete(final Long id) {
        szervezetRepository.deleteById(id);
    }
}
