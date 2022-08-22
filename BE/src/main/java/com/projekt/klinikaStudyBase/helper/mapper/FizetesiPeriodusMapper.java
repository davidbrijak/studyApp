package com.projekt.klinikaStudyBase.helper.mapper;

import com.projekt.klinikaStudyBase.data.entity.FizetesiPeriodus;
import com.projekt.klinikaStudyBase.dto.FizetesiPeriodusDetailDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;

/**
 * {@link FizetesiPeriodus} entitas mappeleseert felelols interfesz
 */
@Mapper
public interface FizetesiPeriodusMapper {

    @IterableMapping(qualifiedByName = "mapToDetailDto")
    List<FizetesiPeriodusDetailDto> mapToDetailDtoList(final List<FizetesiPeriodus> fizetesiPeriodus);

    @Named("mapToDetailDto")
    @Mapping(target = "napok", source = "napok")
    FizetesiPeriodusDetailDto mapToDetailDto(final FizetesiPeriodus fizetesiPeriodus);

    FizetesiPeriodus mapToEntity(final FizetesiPeriodusDetailDto fizetesiPeriodusDetailDto);
}
