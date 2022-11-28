package com.projekt.klinikaStudyBase.data.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Meres {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String feltoltesSzam;

    @ManyToOne
    @JoinColumn(name = "alany_id")
    private Szemely alany;

    @ManyToOne
    @JoinColumn(name = "operator_1_id")
    private Szemely operator_1;

    @ManyToOne
    @JoinColumn(name = "operator_2_id")
    private Szemely operator_2;

    @ManyToOne
    @JoinColumn(name = "vizsgaloOrvos_id")
    private Szemely vizsgaloOrvos;

    @ManyToOne
    @JoinColumn(name = "vizsgalatiMod_id")
    private VizsgalatiMod vizsgalatiMod;

    @ManyToOne
    @JoinColumn(name="studyId", nullable=false)
    private Study study;

    private boolean isFizetveBySzervezet;

    private boolean isFizetveToDolgozok;

    private boolean isFizetveToOp1;

    private boolean isFizetveToOp2;

    private boolean isFizetveToVo;

    private Date felszolitoEmailDatum;

    private String megjegyzes;

    private Date datum;

    private Date fizetesiHatarIdo;
}
