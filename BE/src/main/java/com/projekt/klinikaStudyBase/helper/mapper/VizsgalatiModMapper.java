package com.projekt.klinikaStudyBase.helper.mapper;

import com.projekt.klinikaStudyBase.data.entity.VizsgalatiMod;
import com.projekt.klinikaStudyBase.dto.VizsgalatiModDetailDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface VizsgalatiModMapper {

    @IterableMapping(qualifiedByName = "toDto")
    List<VizsgalatiModDetailDto> toListDto(final List<VizsgalatiMod> vizsgalatiMod);

    @Named("toDto")
    @Mapping(target = "eur", source = "eur")
    @Mapping(target = "huf", source = "huf")
    VizsgalatiModDetailDto toDto(final VizsgalatiMod vizsgalatiMod);

    VizsgalatiMod toEntity(final VizsgalatiModDetailDto vizsgalatiMod);
}
