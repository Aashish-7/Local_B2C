package com.b2c.Local.B2C.auths.model;

import com.b2c.Local.B2C.auths.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Entity
public class OauthUser implements OAuth2User {

    @Id
    private String id;

    private String name;

    private String login;

    private String email;

    @Column(length = 450)
    private String accessToken;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(getRole().getName()));
    }

    @Override
    public Map<String, Object> getAttributes() {
        Map<String, Object> attributes = new HashMap<>();
            attributes.put("id", this.getId());
            attributes.put("name", this.getName());
            attributes.put("login", this.getLogin());
            attributes.put("email", this.getEmail());
        return attributes;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
