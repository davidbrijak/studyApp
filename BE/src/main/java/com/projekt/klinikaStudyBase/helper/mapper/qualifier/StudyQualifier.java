package com.projekt.klinikaStudyBase.helper.mapper.qualifier;

import com.projekt.klinikaStudyBase.data.entity.FizetesiPeriodus;
import com.projekt.klinikaStudyBase.data.entity.Meres;
import com.projekt.klinikaStudyBase.data.entity.Szemely;
import com.projekt.klinikaStudyBase.data.entity.Szervezet;
import com.projekt.klinikaStudyBase.dto.FizetesiPeriodusDetailDto;
import com.projekt.klinikaStudyBase.dto.MeresDetailDto;
import com.projekt.klinikaStudyBase.dto.SzemelyDetailDto;
import com.projekt.klinikaStudyBase.dto.SzervezetDetailDto;
import com.projekt.klinikaStudyBase.helper.mapper.FizetesiPeriodusMapper;
import com.projekt.klinikaStudyBase.helper.mapper.MeresMapper;
import com.projekt.klinikaStudyBase.helper.mapper.SzemelyMapper;
import com.projekt.klinikaStudyBase.helper.mapper.SzervezetMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class StudyQualifier {

	private final FizetesiPeriodusMapper fizetesiPeriodusMapper;
	private final SzemelyMapper szemelyMapper;
	private final SzervezetMapper szervezetMapper;
	private final MeresMapper meresMapper;

	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface FizetesiPeriodusQualifer {
	}

	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface SzemelyQualifer {
	}

	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface SzervezetQualifier {
	}

	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface MeresekQualifier {
	}

	@FizetesiPeriodusQualifer
	public FizetesiPeriodusDetailDto setFizetesiPeriodus(final FizetesiPeriodus fizetesiPeriodus) {
		return fizetesiPeriodusMapper.mapToDetailDto(fizetesiPeriodus);
	}

	@SzemelyQualifer
	public SzemelyDetailDto setSzemely(final Szemely szemely) {
		return szemelyMapper.toDto(szemely);
	}


	@SzervezetQualifier
	public SzervezetDetailDto setSzervet(final Szervezet szervezet) {
		return szervezetMapper.toDto(szervezet);
	}

	@MeresekQualifier
	public List<MeresDetailDto> setMeresek(final Set<Meres> meresSet) {
		if (CollectionUtils.isEmpty(meresSet)) {
			return null;
		}
		List<Meres> meresek = new ArrayList<>();
		meresek.addAll(meresSet);
		return meresMapper.toListDto(meresek);
	}
}
