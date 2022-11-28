package com.projekt.klinikaStudyBase.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * A funkciok elereseert felelos jogosultsagokert felelos entitast
 * enkapszulalo osztaly
 *
 * @author brijakd
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Jogosultsag {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String hivatkozasiNev;

    @Column
    private String megnevezes;

    @ManyToMany(mappedBy = "jogosultsagok")
    @JsonIgnore
    private Set<User> felhasznalok = new HashSet<>();

    public void addUser(User user) {
        this.felhasznalok.add(user);
    }
}
