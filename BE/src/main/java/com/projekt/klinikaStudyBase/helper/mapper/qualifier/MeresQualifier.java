package com.projekt.klinikaStudyBase.helper.mapper.qualifier;

import com.projekt.klinikaStudyBase.data.entity.Study;
import com.projekt.klinikaStudyBase.data.entity.Szemely;
import com.projekt.klinikaStudyBase.data.entity.VizsgalatiMod;
import com.projekt.klinikaStudyBase.dto.NewMeresDto;
import com.projekt.klinikaStudyBase.repository.StudyRepository;
import com.projekt.klinikaStudyBase.repository.SzemelyRepository;
import com.projekt.klinikaStudyBase.repository.VizsgalatiModRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class MeresQualifier {

    private final SzemelyRepository szemelyRepository;
    private final VizsgalatiModRepository vizsgalatiModRepository;
    private final StudyRepository studyRepository;

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface SzemelyekQualifier {
    }

    @SzemelyekQualifier
    public Szemely setSzemely(final Long szemelyId) {
        if (szemelyId == null) {
            return null;
        }
        return szemelyRepository.findById(szemelyId).orElse(null);
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface VizsgalatQualifier {
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface FizetesiHataridoQualifier {
    }

    @VizsgalatQualifier
    public VizsgalatiMod setVizsgalatiMod(final Long vizsgalatiModAzonosito) {
        return vizsgalatiModRepository.findById(vizsgalatiModAzonosito)
                .orElse(null);
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface StudyQualifier {
    }

    @StudyQualifier
    public Study setSutdy(final Long studyAzonosito) {
        return studyRepository.findById(studyAzonosito)
                .orElseThrow(null);
    }

    @FizetesiHataridoQualifier
    public Date setFizetesiHatarido(final NewMeresDto meres) {
        final Study study = setSutdy(meres.getStudyAzonosito());
        if (study == null) {
            return null;
        }
        return getFizetesiHatarido(meres.getDatum(), study.getFizetesiPeriodus().getNapok());
    }

    private Date convertToDateViaSqlDate(final LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    private Date getFizetesiHatarido(final Date date, final int days) {
        LocalDate localDate = Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate result = localDate.plusDays(days);
        return convertToDateViaSqlDate(result);
    }
}
