package com.projekt.klinikaStudyBase.helper.mapper;

import com.projekt.klinikaStudyBase.data.entity.KifizetesekKezelese;
import com.projekt.klinikaStudyBase.data.entity.Szemely;
import com.projekt.klinikaStudyBase.dto.FizetesCheckListDto;
import com.projekt.klinikaStudyBase.dto.NewSzemelyDto;
import com.projekt.klinikaStudyBase.dto.SzemelyDetailDto;
import com.projekt.klinikaStudyBase.dto.SzemelyMeresHelper;
import com.projekt.klinikaStudyBase.helper.mapper.qualifier.SzemelyQualifier;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * {@link Szemely} entitas mappeleseert felelols interfesz
 */
@Mapper(uses = SzemelyQualifier.class)
public interface SzemelyMapper {

    @IterableMapping(qualifiedByName = "toSzemelyDto")
    @Mapping(target = "szerepkorok", ignore = true)
    List<SzemelyDetailDto> toListDto(final List<Szemely> szemelyDetailDtos);

    @Named("toSzemelyDto")
    @Mapping(target = "telefonSzam", source = "telefon")
    @Mapping(target = "postaCim", source = "postacim")
    @Mapping(target = "tajszam", source = "tajSzam")
    @Mapping(target = "szerepkorok", source = "szerepkorok", qualifiedBy = SzemelyQualifier.SzerepkorQualifier.class)
    SzemelyDetailDto toDto(final Szemely szemelyDetailDto);

    @Mapping(target = "telefon", source = "telefonSzam")
    @Mapping(target = "tajSzam", source = "tajszam")
    @Mapping(target = "postacim", source = "postaCim")
    @Mapping(target = "szerepkorok", source = "szerepkorAzonositok",
            qualifiedBy = SzemelyQualifier.SzerepkorQualifier.class)
    Szemely toEntity(final NewSzemelyDto szemelyDetailDto);

    @IterableMapping(qualifiedByName = "toFizetesiCheckElement")
    List<FizetesCheckListDto> toFizetesiCheckList(final List <KifizetesekKezelese> szemely);

    @Named("toFizetesiCheckElement")
    @Mapping(target = "szemelyId", source = "kifizetesekKezelese.szemely.id")
    @Mapping(target = "szemely", source = "kifizetesekKezelese.szemely.nev")
    @Mapping(target = "osszeg", source = "szemely", qualifiedBy = SzemelyQualifier.OsszegQualifier.class)
    FizetesCheckListDto toFizetesiCheckElement(final KifizetesekKezelese kifizetesekKezelese);

    @Mapping(target = "szemelyId", source = "szemelyMeresHelper.szemely.id")
    @Mapping(target = "szemely", source = "szemelyMeresHelper.szemely.nev")
    @Mapping(target = "osszeg", source = "szemelyMeresHelper", qualifiedBy = SzemelyQualifier.OsszegQualifier.class)
    @Mapping(target = "meresId", source = "szemelyMeresHelper.meres.id")
    @Mapping(target = "szerepkor", source = "szemelyMeresHelper.szerepkorHivatkozasiNev")
    @Mapping(target = "meresFeltoltesSzam", source = "szemelyMeresHelper.meres.feltoltesSzam")
    @Mapping(target = "fizetveBySzervezet", source = "fizetveBySzervezet")
    @Mapping(target = "fizetveToSzemely", source = "fizetveToSzemely")
    FizetesCheckListDto toFizetesiCheckElementMeresenKent(final SzemelyMeresHelper szemelyMeresHelper);
}
