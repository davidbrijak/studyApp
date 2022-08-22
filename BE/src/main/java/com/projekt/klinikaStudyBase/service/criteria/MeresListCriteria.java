package com.projekt.klinikaStudyBase.service.criteria;

import com.projekt.klinikaStudyBase.data.entity.Meres;
import com.projekt.klinikaStudyBase.data.entity.Meres_;
import com.projekt.klinikaStudyBase.dto.MeresFilterDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class MeresListCriteria extends AbstractCriteria<Meres> {

    public Specification<Meres> create(final MeresFilterDto filter) {
        return filter == null ? Specification.where(null) : Specification.<Meres>where(null)
                .and(like(Meres_.FELTOLTES_SZAM, filter.getFeltoltesSzam()));
    }
}
