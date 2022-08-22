package com.projekt.klinikaStudyBase.repository;

import com.projekt.klinikaStudyBase.data.entity.Szemely;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SzemelyRepository extends JpaRepository<Szemely,Long>, JpaSpecificationExecutor<Szemely> {
	Boolean existsSzemelyByTajSzam(String taj);

	@Query(nativeQuery = true,
			value = "SELECT *" +
					" FROM szemelyek szemely" +
					" JOIN meres meres ON meres.operator_1_id = szemely.id" +
					" OR meres.operator_2_id = szemely.id OR meres.vizsgalo_orvos_id = szemely.id" +
					" WHERE meres.fizetesi_hatar_ido < now() AND (is_fizetve_by_szervezet = FALSE OR" +
					" meres.is_fizetve_to_dolgozok = false)" +
					"GROUP BY szemely.id")
	List<Szemely> getKifizetendoMereseKBySzemely();

}