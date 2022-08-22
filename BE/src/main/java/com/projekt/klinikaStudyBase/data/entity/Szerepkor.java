package com.projekt.klinikaStudyBase.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * A rendszerben meghatarozott szerepkorok.
 *
 * @author brijakd
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SZEREPKOROK")
public class Szerepkor {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String szerepkorMegnevezes;
    @Column
    private String hivatkozasiNev;
    @Column
    private Integer kifizetesAranya; //egy meres utan jaro kifizetes szazalekosan

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "szerepkorok")
    @JsonIgnore
    private Set<Szemely> szemelyek = new HashSet<>();
}
