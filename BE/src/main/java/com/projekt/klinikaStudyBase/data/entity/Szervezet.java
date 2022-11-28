package com.projekt.klinikaStudyBase.data.entity;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SZERVEZET")
public class Szervezet {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nev;

    private String cim;

    private String adoszam;

    private String email;

    @ManyToOne
    @JoinColumn(name="szervezetTipusId", nullable=false)
    private SzervezetTipus szervezetTipus;
}
