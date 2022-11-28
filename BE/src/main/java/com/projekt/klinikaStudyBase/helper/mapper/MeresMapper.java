package com.projekt.klinikaStudyBase.helper.mapper;

import com.projekt.klinikaStudyBase.data.entity.Meres;
import com.projekt.klinikaStudyBase.data.entity.VizsgalatiMod;
import com.projekt.klinikaStudyBase.dto.MeresDetailDto;
import com.projekt.klinikaStudyBase.dto.NewMeresDto;
import com.projekt.klinikaStudyBase.helper.mapper.qualifier.MeresQualifier;
import com.projekt.klinikaStudyBase.helper.mapper.qualifier.StudyQualifier;
import com.projekt.klinikaStudyBase.helper.mapper.qualifier.SzemelyekQualifier;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * {@link Meres} entitas mappeleseert felelols interfesz
 */
@Mapper(uses = {MeresQualifier.class, StudyQualifier.class, SzemelyekQualifier.class})
public interface MeresMapper {

    @Mapping(target = "study", source = "studyAzonosito", qualifiedBy = MeresQualifier.StudyQualifier.class)
    @Mapping(target = "alany", source = "alany.id", qualifiedBy = SzemelyekQualifier.SzemelyQualifer.class)
    @Mapping(target = "operator_1", source = "operator_1.id", qualifiedBy = SzemelyekQualifier.SzemelyQualifer.class)
    @Mapping(target = "operator_2", source = "operator_2.id", qualifiedBy = SzemelyekQualifier.SzemelyQualifer.class)
    @Mapping(target = "vizsgaloOrvos", source = "vizsgaloOrvos.id", qualifiedBy = SzemelyekQualifier.SzemelyQualifer.class)
    @Mapping(target = "vizsgalatiMod", source = "vizsgalatiMod.id", qualifiedBy = MeresQualifier.VizsgalatQualifier.class)
    @Mapping(target = "fizetesiHatarIdo", source = "meres", qualifiedBy = MeresQualifier.FizetesiHataridoQualifier.class)
    Meres toEntity(final NewMeresDto meres);

    @IterableMapping(qualifiedByName = "toDtoMapper")
    List<MeresDetailDto> toListDto(final List<Meres> meresEntity);

    @Named("toDtoMapper")
    @Mapping(target = "vizsgalatiMod", source = "vizsgalatiMod.megnevezes")
    @Mapping(target = "alanyNev", source = "alany.nev")
    @Mapping(target = "taj", source = "alany.tajSzam")
    @Mapping(target = "operator1IdNev", source = "operator_1.nev")
    @Mapping(target = "operator2IdNev", source = "operator_2.nev")
    @Mapping(target = "vizsgaloOrvosNev", source = "vizsgaloOrvos.nev")
    @Mapping(target = "vizsgalatAra", source = "meresEntity.vizsgalatiMod.huf")
    MeresDetailDto toDto(final Meres meresEntity);

    @Mapping(target = "vizsgalatiMod", source = "vizsgalatiMod")
    @Mapping(target = "alany", source = "alany", qualifiedBy = StudyQualifier.SzemelyQualifer.class)
    @Mapping(target = "operator_1", source = "operator_1", qualifiedBy = StudyQualifier.SzemelyQualifer.class)
    @Mapping(target = "operator_2", source = "operator_2", qualifiedBy = StudyQualifier.SzemelyQualifer.class)
    @Mapping(target = "vizsgaloOrvos", source = "vizsgaloOrvos", qualifiedBy = StudyQualifier.SzemelyQualifer.class)
    @Mapping(target = "feltoltesSzam", source = "feltoltesSzam")
    NewMeresDto toNewMeres(final Meres meresEntity);
}
