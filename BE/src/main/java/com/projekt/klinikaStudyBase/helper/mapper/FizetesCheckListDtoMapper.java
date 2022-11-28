package com.projekt.klinikaStudyBase.helper.mapper;

import com.projekt.klinikaStudyBase.data.entity.Meres;
import com.projekt.klinikaStudyBase.data.entity.Szemely;
import com.projekt.klinikaStudyBase.data.entity.Szerepkor;
import com.projekt.klinikaStudyBase.data.enums.SzerepkorEnum;
import com.projekt.klinikaStudyBase.dto.FizetesCheckListDto;
import com.projekt.klinikaStudyBase.helper.mapper.qualifier.SzemelyekQualifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Objects;

@Mapper(uses = {SzemelyekQualifier.class})
public interface FizetesCheckListDtoMapper {
/*
	@Mapping(target = "szemely", source = "szemely.nev")
	@Mapping(target = "szerepkor", expression = "java(szerepkor.getMegnevezes())")
	@Mapping(target = "osszeg", expression = "java(getOsszeg(meres, szemely))")
	//@Mapping(target = "isFizetveBySzervezet", source = "meres.isFizetveBySzervezet")
	//@Mapping(target = "isFizetveToSzemely", source = "meres.isFizetveToDolgozok")
	@Mapping(target = "szemelyId", source = "szemely.id")
	FizetesCheckListDto mapToFizetendoCheckList(final Meres meres, final Szemely szemely, final SzerepkorEnum szerepkor);*/

	default String getOsszeg(final Meres meres, final Szemely szemely) {
		Integer kifizetesAranya = 0;
		final Double meresAraForintban = meres.getVizsgalatiMod().getHuf();

		if (Objects.equals(szemely, meres.getOperator_1())) {
			kifizetesAranya += getKifizetesAranyaBySzerepkor(szemely, SzerepkorEnum.OPERATOR_1.name());
		} else if (Objects.equals(szemely, meres.getOperator_2())) {
			kifizetesAranya += getKifizetesAranyaBySzerepkor(szemely, SzerepkorEnum.OPERATOR_2.name());
		} else if (Objects.equals(szemely, meres.getVizsgaloOrvos())) {
			kifizetesAranya += getKifizetesAranyaBySzerepkor(szemely, SzerepkorEnum.VIZSGALO_ORVOS.name());
		}

		return Double.toString(kifizetesAranya
				* meresAraForintban);
	}

	private Integer getKifizetesAranyaBySzerepkor(final Szemely szemely, final String szerepkorHivatkozasiNev) {
		Integer res =  szemely.getSzerepkorok().stream()
				.filter(szerepkor -> szerepkor.getHivatkozasiNev().equals(szerepkorHivatkozasiNev))
				.findFirst()
				.map(Szerepkor::getKifizetesAranya)
				.orElse(0);
		return res;
	}
}
