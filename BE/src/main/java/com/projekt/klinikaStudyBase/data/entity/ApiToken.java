package com.projekt.klinikaStudyBase.data.entity;

import lombok.*;
import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiToken {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    @ManyToOne
    private User user;
    private String name;
    private OffsetDateTime createdDate;

    public ApiToken(User user, String token, String name, OffsetDateTime createdDate) {
        this.user = user;
        this.token = token;
        this.name = name;
        this.createdDate = createdDate;
    }
}
