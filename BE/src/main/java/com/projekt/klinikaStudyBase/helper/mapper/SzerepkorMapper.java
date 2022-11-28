package com.projekt.klinikaStudyBase.helper.mapper;

import com.projekt.klinikaStudyBase.dto.SzerepkorDetailDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import com.projekt.klinikaStudyBase.data.entity.Szerepkor;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;

/**
 * {@link Szerepkor} entitas mappeleseert felelos interfesz
 */
@Mapper
public interface SzerepkorMapper {

    @IterableMapping(qualifiedByName = "toSzerepkorDto")
    List<SzerepkorDetailDto> toListDto(final List<Szerepkor> szerepkor);

    @Named("toSzerepkorDto")
    @Mapping(target = "megnevezes", source = "szerepkorMegnevezes")
    @Mapping(target = "kifizetesAranya", source = "kifizetesAranya")
    SzerepkorDetailDto toDto(final Szerepkor szerepkor);

    @Mapping(target = "szerepkorMegnevezes", source = "megnevezes")
    @Mapping(target = "kifizetesAranya", source = "kifizetesAranya")
    Szerepkor toEntity(final SzerepkorDetailDto szerepkor);
}
