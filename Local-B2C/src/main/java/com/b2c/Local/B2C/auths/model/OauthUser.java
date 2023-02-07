package com.b2c.Local.B2C.auths.model;

import com.b2c.Local.B2C.auths.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter
@NoArgsConstructor
public class OauthUser {

    @Id
    private String id;

    private String name;

    private String login;

    private String email;

    @Column(length = 450)
    private String accessToken;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 2000)
    private String clientRegistration;

}
