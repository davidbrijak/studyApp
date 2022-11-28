package com.projekt.klinikaStudyBase.helper.mapper;

import com.projekt.klinikaStudyBase.data.entity.SzervezetTipus;
import com.projekt.klinikaStudyBase.dto.SzervezetTipusDetailDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;

@Mapper
public interface SzervezetTipusMapper {

    @IterableMapping(qualifiedByName = "toDto")
    List<SzervezetTipusDetailDto> toListDto(final List<SzervezetTipus> szervezetTipus);

    @Named("toDto")
    @Mapping(target = "nev", source = "megnevezes")
    SzervezetTipusDetailDto toDto(final SzervezetTipus szervezetTipus);

    @Mapping(target = "megnevezes", source = "nev")
    SzervezetTipus toEntity(final SzervezetTipusDetailDto szervezetTipus);
}
