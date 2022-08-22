package com.projekt.klinikaStudyBase.service.impl;

import com.projekt.klinikaStudyBase.dto.FizetesiPeriodusDetailDto;
import com.projekt.klinikaStudyBase.exception.FizetesiModNotFoundException;
import com.projekt.klinikaStudyBase.helper.mapper.FizetesiPeriodusMapper;
import com.projekt.klinikaStudyBase.repository.FizetesiPeriodusRepository;
import com.projekt.klinikaStudyBase.service.FizetesiPeriodusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Fizetesi periodusert felelos service implementacio
 */
@Service
@RequiredArgsConstructor
public class FizetesiPeriodusServiceImpl implements FizetesiPeriodusService {

    private final FizetesiPeriodusRepository fizetesiPeriodusRepository;
    private final FizetesiPeriodusMapper fizetesiPeriodusMapper;

    @Override
    public FizetesiPeriodusDetailDto add(final FizetesiPeriodusDetailDto newFizetesiperiodusDetailDto) {
        final var entity = fizetesiPeriodusMapper.mapToEntity(newFizetesiperiodusDetailDto);
        final var savedEntity = fizetesiPeriodusRepository.save(entity);
        return fizetesiPeriodusMapper.mapToDetailDto(savedEntity);
    }

    @Override
    public FizetesiPeriodusDetailDto find(final Long id) {
        final var foundEntity = fizetesiPeriodusRepository.findById(id)
                .orElseThrow(
                        () -> new FizetesiModNotFoundException(id.toString())
                );
        return fizetesiPeriodusMapper.mapToDetailDto(foundEntity);
    }

    @Override
    public List<FizetesiPeriodusDetailDto> findAll() {
        final var foundEntityList = fizetesiPeriodusRepository.findAll();
        return fizetesiPeriodusMapper.mapToDetailDtoList(foundEntityList);
    }

    @Override
    public void delete(final Long id) {
        fizetesiPeriodusRepository.deleteById(id);
    }
}
