package com.b2c.Local.B2C.auths.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "user_security_details")
@Getter
@Setter@AllArgsConstructor
@NoArgsConstructor
public class UserSecurityDetails {

    @Id
    private UUID id = UUID.randomUUID();

    private Integer maxSession;

    @OneToOne
    private User user;
}
