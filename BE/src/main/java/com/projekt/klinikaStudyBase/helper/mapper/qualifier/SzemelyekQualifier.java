package com.projekt.klinikaStudyBase.helper.mapper.qualifier;

import com.projekt.klinikaStudyBase.data.entity.Szemely;
import com.projekt.klinikaStudyBase.repository.SzemelyRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@RequiredArgsConstructor
public class SzemelyekQualifier {

	private final SzemelyRepository szemelyRepository;

	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface SzemelyQualifer {
	}

	@SzemelyQualifer
	public Szemely setSzemely(final Long szemelyId) {
		if (szemelyId == null) {
			return null;
		}

		Szemely foundSzemely = szemelyRepository.findById(szemelyId)
				.orElse(null);
		return szemelyRepository.findById(szemelyId)
				.orElse(null);
	}
}
