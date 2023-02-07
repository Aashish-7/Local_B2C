package com.b2c.Local.B2C.auths.service;

import com.b2c.Local.B2C.auths.dao.OauthUserRepository;
import com.b2c.Local.B2C.auths.enums.Role;
import com.b2c.Local.B2C.auths.model.OauthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OauthUserService extends DefaultOAuth2UserService {

    @Autowired
    OauthUserRepository oauthUserRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User auth2User = super.loadUser(userRequest);
        OauthUser oauthUser = new OauthUser();
        oauthUser.setId(auth2User.getName());
        oauthUser.setAccessToken(userRequest.getAccessToken().getTokenValue());
        oauthUser.setRole(Role.USER);
        oauthUser.setLogin(auth2User.getAttribute("login"));
        oauthUser.setName(auth2User.getAttribute("name"));
        oauthUser.setClientRegistration(userRequest.getClientRegistration().toString());
        oauthUserRepository.save(oauthUser);
        return auth2User;
    }
}
