package com.projekt.klinikaStudyBase.data.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Study {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String studyKod;

    private String cim;

    private Date szerzodesKotesDatuma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kutatasVezeto_id")
    private Szemely kutatasVezeto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monitor_id")
    private Szemely monitor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "penzugyiKontakt_id")
    private Szemely penzugyiKontakt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "megbizoCeg_id")
    private Szervezet megbizoCeg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cro_id")
    private Szervezet cro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reszleg_id")
    private Szervezet reszleg;

    @ManyToOne
    @JoinColumn(name = "fizetesi_periodus_id", nullable = false)
    private FizetesiPeriodus fizetesiPeriodus;

    @OneToMany(mappedBy = "study")
    private Set<Meres> meresek = new HashSet<>();
}
