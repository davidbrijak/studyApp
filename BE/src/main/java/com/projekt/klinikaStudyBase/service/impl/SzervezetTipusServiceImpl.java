package com.projekt.klinikaStudyBase.service.impl;

import com.projekt.klinikaStudyBase.data.entity.SzervezetTipus;
import com.projekt.klinikaStudyBase.dto.SzervezetTipusDetailDto;
import com.projekt.klinikaStudyBase.exception.FizetesiModNotFoundException;
import com.projekt.klinikaStudyBase.helper.mapper.SzervezetTipusMapper;
import com.projekt.klinikaStudyBase.repository.SzervezetTipusRepository;
import com.projekt.klinikaStudyBase.service.SzervezetTipusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SzervezetTipusServiceImpl implements SzervezetTipusService {

    private final SzervezetTipusRepository szervezetTipusRepository;
    private final SzervezetTipusMapper szervezetTipusMapper;

    @Override
    public SzervezetTipusDetailDto add(final SzervezetTipusDetailDto newSzervezetTipusDto) {
        final var szervezetTipus = szervezetTipusMapper.toEntity(newSzervezetTipusDto);
        final var savedSzervezetTipus = szervezetTipusRepository.save(szervezetTipus);
        return szervezetTipusMapper.toDto(savedSzervezetTipus);
    }

    @Override
    public List<SzervezetTipusDetailDto> findAll() {
        List<SzervezetTipus> szervezets = szervezetTipusRepository.findAll();
        return szervezetTipusMapper.toListDto(szervezets);
    }

    @Override
    public void delete(final Long id) {
        szervezetTipusRepository.deleteById(id);
    }

    @Override
    public SzervezetTipusDetailDto find(final Long id) {
        final var foundEntity = szervezetTipusRepository.findById(id)
                .orElseThrow(() -> new FizetesiModNotFoundException(id.toString()));
        return szervezetTipusMapper.toDto(foundEntity);
    }
}
