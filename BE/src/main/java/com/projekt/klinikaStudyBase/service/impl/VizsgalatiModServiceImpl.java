package com.projekt.klinikaStudyBase.service.impl;

import com.projekt.klinikaStudyBase.data.entity.VizsgalatiMod;
import com.projekt.klinikaStudyBase.dto.VizsgalatiModDetailDto;
import com.projekt.klinikaStudyBase.exception.VizsgalatiModNotFoundException;
import com.projekt.klinikaStudyBase.helper.mapper.VizsgalatiModMapper;
import com.projekt.klinikaStudyBase.repository.VizsgalatiModRepository;
import com.projekt.klinikaStudyBase.service.VizsgalatiModService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VizsgalatiModServiceImpl implements VizsgalatiModService {

    private final VizsgalatiModRepository vizsgalatiModRepository;
    private final VizsgalatiModMapper vizsgalatiModMapper;

    @Override
    public VizsgalatiModDetailDto add(final VizsgalatiModDetailDto newVizsgalatiMod) {
        final var vizsgalat = vizsgalatiModMapper.toEntity(newVizsgalatiMod);
        final var savedViszgalatiMod = vizsgalatiModRepository.save(vizsgalat);
        return vizsgalatiModMapper.toDto(savedViszgalatiMod);
    }

    @Override
    public List<VizsgalatiModDetailDto> findAll() {
        final List<VizsgalatiMod> vizsgalatiMods = vizsgalatiModRepository.findAll();
        return vizsgalatiModMapper.toListDto(vizsgalatiMods);
    }

    @Override
    public VizsgalatiModDetailDto findById(final Long id) {
        final var foundViszgalatiMod = vizsgalatiModRepository.findById(id)
                .orElseThrow(() -> new VizsgalatiModNotFoundException(id.toString()));
        return vizsgalatiModMapper.toDto(foundViszgalatiMod);
    }

    @Override
    public void deleteById(final Long id) {
        vizsgalatiModRepository.deleteById(id);
    }
}
