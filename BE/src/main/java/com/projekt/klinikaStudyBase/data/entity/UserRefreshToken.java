package com.projekt.klinikaStudyBase.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRefreshToken {

    @Id
    @GeneratedValue
    private Long id;
    private String token;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
    private Date expiration;

    public UserRefreshToken(User user) {
        this.user = user;
        this.token = UUID.randomUUID().toString();
        this.expiration =
                new Date(Calendar.getInstance().toInstant().plus(30, ChronoUnit.DAYS).toEpochMilli());
    }


    public boolean isValid() {
        return !expiration.before(new Date());
    }

}
