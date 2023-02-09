package com.b2c.Local.B2C.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Configuration
public class CustomOauth2Config {

    @Value("${spring.security.oauth2.client.registration.github.client-secret}")
    String gitSecret;
    @Value("${spring.security.oauth2.client.registration.github.client-id}")
    String gitClientId;

    @Value("${spring.security.oauth2.client.registration.authServer.client-secret}")
    String authSecret;
    @Value("${spring.security.oauth2.client.registration.authServer.client-id}")
    String authClientId;


    @Bean
    public ClientRegistrationRepository clientRepository() {
        ClientRegistration githubRegistration = CommonOAuth2Provider.GITHUB.getBuilder("github").clientId(gitClientId).clientSecret(gitSecret).build();
        return new InMemoryClientRegistrationRepository(githubRegistration, googleClientRegistration());
    }

    private ClientRegistration googleClientRegistration() {
        return ClientRegistration.withRegistrationId("authServer")
                .clientId(authClientId)
                .clientSecret(authSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://authserver:8080/login/oauth2/code/authServer")
                .scope("read")
                .authorizationUri("http://authserver:8000/oauth2/authorize")
                .tokenUri("http://authserver:8000/oauth2/token")
                .userInfoUri("http://authserver:8000/api/user")
                .issuerUri("http://authserver:8000")
                .jwkSetUri("http://authserver:8000/oauth2/jwks")
                .userNameAttributeName("id")
                .clientName("local-b2c")
                .build();
    }

}
