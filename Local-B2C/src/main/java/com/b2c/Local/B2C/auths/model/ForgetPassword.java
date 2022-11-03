package com.b2c.Local.B2C.auths.model;

import com.b2c.Local.B2C.utility.RandomString;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "forget_password")
@Getter
@Setter
@NoArgsConstructor
public class ForgetPassword {

    @Id
    @Column(unique=true, nullable=false, precision=19)
    private UUID id = UUID.randomUUID();

    @Column
    @JsonIgnore
    private String token= RandomString.getRandom(6);

    @Column
    private LocalDateTime ctime;

    @OneToOne
    @JsonIgnore
    private User user;
}
