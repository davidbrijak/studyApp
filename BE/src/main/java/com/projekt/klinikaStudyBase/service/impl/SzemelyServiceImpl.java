package com.projekt.klinikaStudyBase.service.impl;

import com.projekt.klinikaStudyBase.data.entity.Szemely;
import com.projekt.klinikaStudyBase.dto.NewSzemelyDto;
import com.projekt.klinikaStudyBase.dto.SzemelyDetailDto;
import com.projekt.klinikaStudyBase.exception.ExistingSzemelyException;
import com.projekt.klinikaStudyBase.exception.SzemelyNotFoundException;
import com.projekt.klinikaStudyBase.helper.mapper.SzemelyMapper;
import com.projekt.klinikaStudyBase.repository.SzemelyRepository;
import com.projekt.klinikaStudyBase.service.SzemelyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SzemelyServiceImpl implements SzemelyService {

	private final SzemelyRepository szemelyRepository;
	private final SzemelyMapper szemelyMapper;

	@Override
	public SzemelyDetailDto addSzemelyWithSzerepkor(final NewSzemelyDto newSzemelyDto) {
		final String taj = newSzemelyDto.getTajszam();
		final Boolean isExist = szemelyRepository.existsSzemelyByTajSzam(taj);
		if (isExist) {
			throw new ExistingSzemelyException(taj);
		}
		final Szemely szemely = szemelyMapper.toEntity(newSzemelyDto);
		final Szemely savedSzemely = szemelyRepository.save(szemely);
		return szemelyMapper.toDto(savedSzemely);
	}

	@Override
	public SzemelyDetailDto findbyId(final Long id) {
		final Szemely entity = szemelyRepository.findById(id)
				.orElseThrow(() -> new SzemelyNotFoundException(id.toString()));
		return szemelyMapper.toDto(entity);
	}

	@Override
	public List<SzemelyDetailDto> findAll() {
		List<Szemely> szemelyek = szemelyRepository.findAll();
		return szemelyMapper.toListDto(szemelyek);
	}
}
