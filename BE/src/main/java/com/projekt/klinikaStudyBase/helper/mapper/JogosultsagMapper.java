package com.projekt.klinikaStudyBase.helper.mapper;

import com.projekt.klinikaStudyBase.data.entity.Jogosultsag;
import com.projekt.klinikaStudyBase.dto.JogosultsagDetailDto;
import org.mapstruct.*;

import java.util.List;

/**
 * {@link Jogosultsag} entitas mappeleseert felelols interfesz
 */
@Mapper
public interface JogosultsagMapper {

    @Mapping(target = "megnevezes", source = "jogosultsagNev")
    Jogosultsag mapToJogosultsagEntity(final JogosultsagDetailDto jogosultsagDetailDto);

    @IterableMapping(qualifiedByName = "mapToDto")
    List<JogosultsagDetailDto> mapToDtoList(final List<Jogosultsag> jogosultsag);

    @Named("mapToDto")
    @Mapping(target = "jogosultsagNev", source = "megnevezes")
    JogosultsagDetailDto mapToDto(final Jogosultsag jogosultsag);
}
