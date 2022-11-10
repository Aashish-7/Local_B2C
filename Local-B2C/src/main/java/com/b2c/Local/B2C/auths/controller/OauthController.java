package com.b2c.Local.B2C.auths.controller;

import com.b2c.Local.B2C.auths.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class OauthController {

    private OauthService oauthService;

    private OAuth2AuthorizedClientService service;


    @Autowired
    public OauthController(OauthService oauthService, OAuth2AuthorizedClientService service) {
        this.oauthService = oauthService;
        this.service = service;
    }

    @GetMapping("/oauthLogin")
    public OAuth2AuthenticationToken testToken(OAuth2AuthenticationToken token, OAuth2AuthorizedClientService service){
        var t = service.loadAuthorizedClient(token.getAuthorizedClientRegistrationId(), token.getName());
        System.out.println(t.getAccessToken().getTokenValue());
        return token;
    }

        @GetMapping("/")
        public String message(Principal principal) {
            return "Welcome "+principal.getName()+". You are successfully logged in";
        }
}
