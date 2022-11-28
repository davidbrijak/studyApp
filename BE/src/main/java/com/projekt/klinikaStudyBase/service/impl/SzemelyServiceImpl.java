package com.projekt.klinikaStudyBase.service.impl;

import com.projekt.klinikaStudyBase.data.entity.Szemely;
import com.projekt.klinikaStudyBase.data.entity.Szerepkor;
import com.projekt.klinikaStudyBase.dto.NewSzemelyDto;
import com.projekt.klinikaStudyBase.dto.SzemelyDetailDto;
import com.projekt.klinikaStudyBase.dto.SzemelyFilter;
import com.projekt.klinikaStudyBase.exception.ExistingSzemelyException;
import com.projekt.klinikaStudyBase.exception.SzemelyNotFoundException;
import com.projekt.klinikaStudyBase.helper.mapper.SzemelyMapper;
import com.projekt.klinikaStudyBase.repository.SzemelyRepository;
import com.projekt.klinikaStudyBase.repository.SzerepkorRepository;
import com.projekt.klinikaStudyBase.service.SzemelyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SzemelyServiceImpl implements SzemelyService {

	private final SzemelyRepository szemelyRepository;
	private final SzemelyMapper szemelyMapper;

	private final SzerepkorRepository szerepkorRepository;

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

	@Override
	public List<SzemelyDetailDto> search(final SzemelyFilter szemelyFilter) {
		List<Szemely> asd = szemelyRepository.search(szemelyFilter.getSzerepkorAzonositok(), szemelyFilter.getNev());
		return szemelyMapper.toListDto(asd);
	}

	@Override
	public List<SzemelyDetailDto> searchPageAble(PageRequest pageRequest) {
		Page<Szemely> asd = szemelyRepository.findAll(PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize()));
		return szemelyMapper.toListDto(asd.getContent());
	}

	@Override
	public boolean hasAnySzemelyBySzerepkor(Long szerepkorId) {
		return szemelyRepository.existsSzemelyBySzerepkor(szerepkorId) > 0;
	}
}
