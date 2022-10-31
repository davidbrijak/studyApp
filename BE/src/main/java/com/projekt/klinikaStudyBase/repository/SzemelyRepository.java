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
	List<Szemely> getKifizetendoMeresekBySzemely();

	@Query(nativeQuery = true,
			value = "SELECT * FROM szemelyek szemelyek join szemely_szerepkor_mapping" +
					" szemely_szerepkor on szemely_szerepkor.szemely_id = id where " +
					" szerepkor_id in :szerepkorok" +
					" and (:nev is null or szemelyek.nev like %:nev%)")
	List<Szemely> search(List<Long> szerepkorok, String nev);

	@Query(nativeQuery = true,
			value = "SELECT COUNT(szemely_id)" +
					" FROM szemely_szerepkor_mapping" +
					" WHERE szerepkor_id = :szerepkorId")
	int existsSzemelyBySzerepkor(Long szerepkorId);
}