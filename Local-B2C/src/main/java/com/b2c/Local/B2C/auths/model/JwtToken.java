package com.b2c.Local.B2C.auths.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@Entity(name = "jwt_token")
public class JwtToken {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(length = 2000)
    private String token;

    private Date createDate;

    private Date expireDate;

    private Boolean active;

    @ManyToOne
    private User user;

}
