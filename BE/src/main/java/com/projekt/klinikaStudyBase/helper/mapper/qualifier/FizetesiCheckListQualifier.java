package com.projekt.klinikaStudyBase.helper.mapper.qualifier;

import com.projekt.klinikaStudyBase.data.entity.Szemely;
import com.projekt.klinikaStudyBase.data.entity.Szerepkor;
import com.projekt.klinikaStudyBase.dto.SzemelyDetailDto;
import com.projekt.klinikaStudyBase.dto.SzerepkorDetailDto;
import com.projekt.klinikaStudyBase.helper.mapper.SzemelyMapper;
import com.projekt.klinikaStudyBase.helper.mapper.SzerepkorMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@RequiredArgsConstructor
public class FizetesiCheckListQualifier {

	private final SzemelyMapper szemelyMapper;
	private final SzerepkorMapper szerepkorMapper;

	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface SzemelyQualifer {
	}

	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface SzerepkorQualifier {
	}

	@SzemelyQualifer
	public SzemelyDetailDto setSzemely(final Szemely szemely) {
		return szemelyMapper.toDto(szemely);
	}

	@SzerepkorQualifier
	public SzerepkorDetailDto setSzerepkor(final Szerepkor szerepkor) {
		return szerepkorMapper.toDto(szerepkor);
	}
}
