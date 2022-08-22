package com.projekt.klinikaStudyBase.helper.mapper.qualifier;

import com.projekt.klinikaStudyBase.data.entity.FizetesiPeriodus;
import com.projekt.klinikaStudyBase.repository.FizetesiPeriodusRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@RequiredArgsConstructor
public class FizetesiPeriodusQualifier {

	private final FizetesiPeriodusRepository fizetesiPeriodusRepository;

	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface FizetesiPeriodusQualifer {
	}

	@FizetesiPeriodusQualifer
	public FizetesiPeriodus setFizetesiPeriodus(final Long id) {
		return fizetesiPeriodusRepository.findById(id)
				.orElse(null);
	}
}
