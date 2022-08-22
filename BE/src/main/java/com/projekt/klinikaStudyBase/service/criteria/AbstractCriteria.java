package com.projekt.klinikaStudyBase.service.criteria;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public abstract class AbstractCriteria<T> {

    protected Specification<T> like(final String field, final String value) {
        if (!StringUtils.hasLength(value)) {
            return null;
        }
        return (root, query, cb) -> cb.like(root.get(field), "%" + value + "%");
    }

    protected Specification<T> isEqual(final String field, final Object value) {
        if(value == null){
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get(field), value);
    }
}
