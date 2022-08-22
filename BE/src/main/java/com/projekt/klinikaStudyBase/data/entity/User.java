package com.projekt.klinikaStudyBase.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column
    private String felhasznaloNev;
    @Column
    private String jelszo;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name ="USER_JOGOSULTSAG_MAPPING",
            joinColumns = @JoinColumn(name ="USER_ID"),
            inverseJoinColumns = @JoinColumn(name ="JOGOSULTSAG_ID"))
    @JsonIgnore
    private Set<Jogosultsag> jogosultsagok = new HashSet<>();

    public void setJogosultsag(Set<Jogosultsag> jogosultsagok) {
        this.jogosultsagok = jogosultsagok;
    }
}
