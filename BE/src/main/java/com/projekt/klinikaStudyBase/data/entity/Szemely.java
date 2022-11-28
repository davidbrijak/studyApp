package com.projekt.klinikaStudyBase.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/*
 * Szerepkorrel lesz osszekapcsolva ez alapjan lehet: beteg, monitor vagy penzugyi kontakt
 * */

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SZEMELYEK")
public class Szemely {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	private String nev;

	@Column
	private String email;

	@Column
	private String telefon;

	@Column
	private String tajSzam;

	@Column
	private String postacim;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "SZEMELY_SZEREPKOR_MAPPING",
			joinColumns = @JoinColumn(name = "SZEMELY_ID"),
			inverseJoinColumns = @JoinColumn(name = "SZEREPKOR_ID"))
	private Set<Szerepkor> szerepkorok = new HashSet<>();

	/*
	@OneToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Meres> meresek = new HashSet<>();*/

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "SZEMELY_SZEREPKOR_MERES_MAPPING",
			joinColumns = @JoinColumn(name = "SZEMELY_ID"),
			inverseJoinColumns = @JoinColumn(name = "SZEREPKOR_ID"))
	private Set<Meres> meresek = new HashSet<>();
}
