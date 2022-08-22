package com.projekt.klinikaStudyBase.repository.impl;

import com.projekt.klinikaStudyBase.data.entity.Meres;
import com.projekt.klinikaStudyBase.data.entity.Szemely;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public class MeresRepositoryCustomImpl {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Meres> findAllFizetendo() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Meres> cq = cb.createQuery(Meres.class);
		Root<Meres> meresRoot = cq.from(Meres.class);

		cq.select(meresRoot)
				.where(
						cb.isFalse(meresRoot.get("isFizetveBySzervezet")),
						cb.isFalse(meresRoot.get("isFizetveToDolgozok")),
						cb.isNull(meresRoot.get("felszolitoEmailDatum")),
						cb.lessThanOrEqualTo(meresRoot.get("fizetesiHatarIdo"),
								convertToDateViaSqlDate(LocalDate.now()))
				);
		return entityManager.createQuery(cq).getResultList();
	}

	private Date convertToDateViaSqlDate(final LocalDate dateToConvert) {
		return java.sql.Date.valueOf(dateToConvert);
	}
}