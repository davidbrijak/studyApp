package com.projekt.klinikaStudyBase.helper.mapper.qualifier;

import com.projekt.klinikaStudyBase.data.entity.Meres;
import com.projekt.klinikaStudyBase.data.entity.Szemely;
import com.projekt.klinikaStudyBase.data.entity.Szerepkor;
import com.projekt.klinikaStudyBase.data.enums.SzerepkorEnum;
import com.projekt.klinikaStudyBase.dto.SzemelyMeresHelper;
import com.projekt.klinikaStudyBase.dto.SzerepkorDetailDto;
import com.projekt.klinikaStudyBase.helper.mapper.SzerepkorMapper;
import com.projekt.klinikaStudyBase.repository.SzerepkorRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SzemelyQualifier {

    private final SzerepkorRepository szerepkorRepository;
    private final SzerepkorMapper szerepkorMapper;

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface SzerepkorQualifier {
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface OsszegQualifier {
    }

    @SzerepkorQualifier
    public Set<Szerepkor> setSzerepkorok(final Set<Long> szerepkorAzonositok) {
        return szerepkorRepository.findAllById(szerepkorAzonositok).stream().collect(Collectors.toSet());
    }

    @SzerepkorQualifier
    public Set<SzerepkorDetailDto> setSzerepkorokDetails(final Set<Szerepkor> szerepkorok) {
        if (CollectionUtils.isEmpty(szerepkorok)) {
            return null;
        }
        return szerepkorMapper.toListDto(
                szerepkorok.stream()
                        .collect(Collectors.toList())).stream()
                .collect(Collectors.toSet());
    }

    @OsszegQualifier
    public String setOsszegSum(final Szemely szemely) {
        Double osszeg = 0D;
        final List<Meres> fizetendoMeresek = szemely.getMeresek()
                .stream()
                .filter(meres -> meres.getFizetesiHatarIdo().before(convertToDateViaSqlDate(LocalDate.now())) &&
                        (!meres.isFizetveBySzervezet() || !meres.isFizetveToDolgozok())
        ).collect(Collectors.toList());

        for (Meres meres : fizetendoMeresek) {
            osszeg += setOsszeg(meres, szemely);
        }
        return osszeg.toString();
    }

    @OsszegQualifier
    public String setOsszegSum(final SzemelyMeresHelper szemely) {
        Double osszeg = 0D;
        osszeg = setOsszeg(szemely.getMeres(), szemely.getSzemely());
        return osszeg.toString();
    }

    private Date convertToDateViaSqlDate(final LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    private Double setOsszeg(final Meres meres, final Szemely szemely) {
        Double osszeg = 0D;
        if (meres.getOperator_1() != null && meres.getOperator_1().equals(szemely)) {
            osszeg += calculateOsszegBySzerepkor(szemely, SzerepkorEnum.OPERATOR_1.name(), meres);
        }
        if (meres.getOperator_2() != null && meres.getOperator_2().equals(szemely)) {
            osszeg += calculateOsszegBySzerepkor(szemely, SzerepkorEnum.OPERATOR_2.name(), meres);
        }
        if (meres.getVizsgaloOrvos().equals(szemely)) {
            osszeg += calculateOsszegBySzerepkor(szemely, SzerepkorEnum.VIZSGALO_ORVOS.name(), meres);
        }
        return osszeg;
    }

    private Double calculateOsszegBySzerepkor(final Szemely szemely, final String hivatkozasiNev, final Meres meres) {
        final Szerepkor szerepkor = szemely.getSzerepkorok().stream()
                .filter(sz -> sz.getHivatkozasiNev().equals(hivatkozasiNev))
                .findFirst()
                .orElse(null);

        if (szerepkor == null) {
            return 0D;
        }
        return szerepkor.getKifizetesAranya() / 100D * meres.getVizsgalatiMod().getHuf();
    }
}
