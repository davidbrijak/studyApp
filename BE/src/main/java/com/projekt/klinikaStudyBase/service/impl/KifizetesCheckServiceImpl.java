package com.projekt.klinikaStudyBase.service.impl;

import com.projekt.klinikaStudyBase.data.entity.KifizetesekKezelese;
import com.projekt.klinikaStudyBase.data.entity.Szemely;
import com.projekt.klinikaStudyBase.dto.FizetesCheckListDto;
import com.projekt.klinikaStudyBase.helper.mapper.SzemelyMapper;
import com.projekt.klinikaStudyBase.repository.KifizetesekKezeleseRepository;
import com.projekt.klinikaStudyBase.repository.SzemelyRepository;
import com.projekt.klinikaStudyBase.service.KifizetesCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KifizetesCheckServiceImpl implements KifizetesCheckService {

	private final SzemelyRepository szemelyRepository;
	private final SzemelyMapper szemelyMapper;
	private final KifizetesekKezeleseRepository kifizetesekKezeleseRepositoryRepo;

	@Override
	public List<FizetesCheckListDto> getCheckListBySzemely() {
		final List<KifizetesekKezelese> szemelyList = kifizetesekKezeleseRepositoryRepo.getKifizetendoMereseKBySzemely();
		return szemelyMapper.toFizetesiCheckList(szemelyList);
	}

	@Override
	public List<FizetesCheckListDto> getCheckListBySzemelyDetail(final Long szemelyId) {
		List<FizetesCheckListDto> fizetesCheckListDtos = new ArrayList<>();
		final Szemely szemely = szemelyRepository.getById(szemelyId);
		final List<com.projekt.klinikaStudyBase.data.entity.KifizetesekKezelese> kifizetesekKezelese =  kifizetesekKezeleseRepositoryRepo.getAllBySzemely(szemely);

		final List<com.projekt.klinikaStudyBase.data.entity.KifizetesekKezelese> fizetendoMeresekSzemelyenkent = kifizetesekKezelese.stream().filter(
				meres -> meres.getFizetesiHatarido().before(convertToDateViaSqlDate(LocalDate.now())) &&
						(!meres.isFizetveBySzervezet() || !meres.isFizetveToSzemely())
		).collect(Collectors.toList());


		for (com.projekt.klinikaStudyBase.data.entity.KifizetesekKezelese kifizetesekKezelese1 :fizetendoMeresekSzemelyenkent) {
			FizetesCheckListDto fizetesCheckListDto = FizetesCheckListDto.builder().meresId(kifizetesekKezelese1.getMeres().getId())
					.id(kifizetesekKezelese1.getId())
					.szemelyId(kifizetesekKezelese1.getSzemely().getId())
					.szemely(szemely.getNev())
					.meresFeltoltesSzam(kifizetesekKezelese1.getMeres().getFeltoltesSzam())
					.szerepkor(kifizetesekKezelese1.getMeresbenBetoltottSzerepkor().name())
					.osszeg(kifizetesekKezelese1.getKifizetesOsszege().toString())
					.fizetveToSzemely(kifizetesekKezelese1.isFizetveToSzemely())
					.fizetveBySzervezet(kifizetesekKezelese1.isFizetveBySzervezet())
					.build();
			fizetesCheckListDtos.add(fizetesCheckListDto);
		}

		return fizetesCheckListDtos;
	}

	@Override
	public void handleChanged(List<FizetesCheckListDto> kifChanged) {
		List<com.projekt.klinikaStudyBase.data.entity.KifizetesekKezelese> changedMeresek = kifizetesekKezeleseRepositoryRepo.findAllById(kifChanged.stream().map(FizetesCheckListDto::getId).collect(Collectors.toSet()));
		for (FizetesCheckListDto kif : kifChanged) {
			com.projekt.klinikaStudyBase.data.entity.KifizetesekKezelese kifizetesekKezelese = changedMeresek.stream().filter(c -> c.getId().equals(kif.getId())).findFirst().get();
			kifizetesekKezelese.setFizetveBySzervezet(kif.isFizetveBySzervezet());
			kifizetesekKezelese.setFizetveToSzemely(kif.isFizetveToSzemely());
		}
		kifizetesekKezeleseRepositoryRepo.saveAll(changedMeresek);
	}

	private Date convertToDateViaSqlDate(final LocalDate dateToConvert) {
		return java.sql.Date.valueOf(dateToConvert);
	}

}
