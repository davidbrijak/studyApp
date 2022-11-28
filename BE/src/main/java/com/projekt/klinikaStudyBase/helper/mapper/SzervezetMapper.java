package com.projekt.klinikaStudyBase.helper.mapper;

import com.projekt.klinikaStudyBase.data.entity.Szervezet;
import com.projekt.klinikaStudyBase.dto.NewSzervezetDto;
import com.projekt.klinikaStudyBase.dto.SzervezetDetailDto;
import com.projekt.klinikaStudyBase.helper.mapper.qualifier.SzervezetekQualifier;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;

/**
 * {@link Szervezet} entitas mappeleseert felelos interfesz
 */
@Mapper(uses = {SzervezetekQualifier.class})
public interface SzervezetMapper {

    @Mapping(target = "szervezetTipus", source = "szervezetTipusAzonosito",
            qualifiedBy = SzervezetekQualifier.SzervezetTipusQualifier.class)
    Szervezet toEntity(final NewSzervezetDto szervezet);

    @IterableMapping(qualifiedByName = "toDto")
    List<SzervezetDetailDto> toListDto(final List<Szervezet> szervezet);

    @Named("toDto")
    @Mapping(target = "szervezetTipusNev", source = "szervezetTipus.megnevezes")
    SzervezetDetailDto toDto(final Szervezet szervezet);
}
