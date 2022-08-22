package com.projekt.klinikaStudyBase.helper.mapper;

import com.projekt.klinikaStudyBase.data.entity.Study;
import com.projekt.klinikaStudyBase.dto.NewStudyDto;
import com.projekt.klinikaStudyBase.dto.StudyDetailDto;
import com.projekt.klinikaStudyBase.dto.StudyListDto;
import com.projekt.klinikaStudyBase.helper.mapper.qualifier.FizetesiPeriodusQualifier;
import com.projekt.klinikaStudyBase.helper.mapper.qualifier.StudyQualifier;
import com.projekt.klinikaStudyBase.helper.mapper.qualifier.SzemelyekQualifier;
import com.projekt.klinikaStudyBase.helper.mapper.qualifier.SzervezetekQualifier;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;

/**
 * {@link Study} entitas mappeleseert felelols interfesz
 */
@Mapper(uses = {
		FizetesiPeriodusQualifier.class,
		StudyQualifier.class,
		SzervezetekQualifier.class,
		SzemelyekQualifier.class
})
public interface StudyMapper {

	@Mapping(target = "megbizoCeg", source = "megbizoCeg.id",
			qualifiedBy = SzervezetekQualifier.SzervezetQualifier.class)
	@Mapping(target = "cro", source = "cro.id",
			qualifiedBy = SzervezetekQualifier.SzervezetQualifier.class)
	@Mapping(target = "reszleg", source = "reszleg.id",
			qualifiedBy = SzervezetekQualifier.SzervezetQualifier.class)
	@Mapping(target = "penzugyiKontakt", source = "penzugyiKontakt.id",
			qualifiedBy = SzemelyekQualifier.SzemelyQualifer.class)
	@Mapping(target = "kutatasVezeto", source = "kutatasVezeto.id",
			qualifiedBy = SzemelyekQualifier.SzemelyQualifer.class)
	@Mapping(target = "monitor", source = "monitor.id",
			qualifiedBy = SzemelyekQualifier.SzemelyQualifer.class)
	@Mapping(target = "fizetesiPeriodus", source = "fizetesiPeriodusDetailDto.id",
			qualifiedBy = FizetesiPeriodusQualifier.FizetesiPeriodusQualifer.class)
	Study toEntity(final NewStudyDto newStudyDto);

	@IterableMapping(qualifiedByName = "toListDtoMapper")
	List<StudyListDto> toListDto(final List<Study> meresEntity);

	@Named("toListDtoMapper")
	StudyListDto toListDto(final Study study);

	@Mapping(target = "cro", source = "cro", qualifiedBy = StudyQualifier.SzervezetQualifier.class)
	@Mapping(target = "megbizoCeg", source = "megbizoCeg", qualifiedBy = StudyQualifier.SzervezetQualifier.class)
	@Mapping(target = "kutatasVezeto", source = "kutatasVezeto", qualifiedBy = StudyQualifier.SzemelyQualifer.class)
	@Mapping(target = "penzugyiKontakt", source = "penzugyiKontakt", qualifiedBy = StudyQualifier.SzemelyQualifer.class)
	@Mapping(target = "szerzodeskotesDatum", source = "szerzodesKotesDatuma")
	@Mapping(target = "fizetesiPeriodusDetailDto",
			source = "fizetesiPeriodus", qualifiedBy = StudyQualifier.FizetesiPeriodusQualifer.class)
	@Mapping(target = "monitor", source = "monitor", qualifiedBy = StudyQualifier.SzemelyQualifer.class)
	@Mapping(target = "reszleg", source = "reszleg", qualifiedBy = StudyQualifier.SzervezetQualifier.class)
	@Mapping(target = "meresek", source = "meresek", qualifiedBy = StudyQualifier.MeresekQualifier.class)
	StudyDetailDto toDto(final Study study);
}
