package com.projekt.klinikaStudyBase.helper.mapper.qualifier;

import com.projekt.klinikaStudyBase.data.entity.Szervezet;
import com.projekt.klinikaStudyBase.data.entity.SzervezetTipus;
import com.projekt.klinikaStudyBase.repository.SzervezetRepository;
import com.projekt.klinikaStudyBase.repository.SzervezetTipusRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@RequiredArgsConstructor
public class SzervezetekQualifier {

	private final SzervezetTipusRepository szervezetTipusRepository;
	private final SzervezetRepository szervezetRepository;

	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface SzervezetTipusQualifier {
	}

	@SzervezetTipusQualifier
	public SzervezetTipus setSzervetTipus(final Long szervezetTipusAzonosito) {
		return szervezetTipusRepository.findById(szervezetTipusAzonosito)
				.orElse(null);
	}

	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface SzervezetQualifier {
	}

	@SzervezetQualifier
	public Szervezet setSzervet(final Long szervezetId) {
		return szervezetRepository.findById(szervezetId)
				.orElse(null);
	}
}
