package com.projekt.klinikaStudyBase.repository;

import com.projekt.klinikaStudyBase.data.entity.KifizetesekKezelese;
import com.projekt.klinikaStudyBase.data.entity.Szemely;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KifizetesekKezeleseRepository extends JpaRepository<KifizetesekKezelese, Long>, JpaSpecificationExecutor<com.projekt.klinikaStudyBase.data.entity.KifizetesekKezelese> {
	List<KifizetesekKezelese> getAllBySzemely(Szemely szemely);

	@Query(nativeQuery = true,
			value = "SELECT *" +
					" FROM kifizetesek_kezelese kifizetesekKezelese" +
					" WHERE fizetesi_hatarido < now() AND (is_fizetve_by_szervezet = FALSE OR" +
					" is_fizetve_to_szemely = false)" +
					"GROUP BY szemely_id")
	List<KifizetesekKezelese> getKifizetendoMereseKBySzemely();
}
