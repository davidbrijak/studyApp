package com.projekt.klinikaStudyBase.data.entity;

import com.projekt.klinikaStudyBase.data.enums.SzerepkorEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KifizetesekKezelese {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "szemely_id", nullable = false)
	private Szemely szemely;

	@Enumerated
	private SzerepkorEnum meresbenBetoltottSzerepkor;

	@ManyToOne
	@JoinColumn(name = "szerepkor_id", nullable = false)
	private Meres meres;

	private boolean isFizetveToSzemely;

	private boolean isFizetveBySzervezet;

	private Date fizetesiHatarido;

	private Double kifizetesOsszege;
}
