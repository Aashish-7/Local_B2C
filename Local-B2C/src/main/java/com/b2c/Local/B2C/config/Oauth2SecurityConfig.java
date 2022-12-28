package com.b2c.Local.B2C.config;


import com.b2c.Local.B2C.auths.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Oauth2SecurityConfig {

    private OauthService oauthService;

    @Autowired
    public Oauth2SecurityConfig(OauthService oauthService) {
        this.oauthService = oauthService;
    }
//
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.authorizeRequests()
//                .antMatchers("/oauthLogin").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll()
//                .loginPage("/oauthLogin")
//                .and()
//                .oauth2Login()
//                .loginPage("/oauthLogin")
//                .userInfoEndpoint()
//                .userService(oauthService);
//        return httpSecurity.build();
//    }


    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .clientRegistrationRepository(clientRepository())
                .authorizedClientService(authorizedClientService());
        return httpSecurity.build();
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new InMemoryOAuth2AuthorizedClientService(
                clientRepository());
    }


    private ClientRegistration clientRegistration() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("spring.security.oauth2.client.registration.github.client-id")
                .clientSecret("spring.security.oauth2.client.registration..github.client-secret")
                .scope("user:email").build();
    }

    @Bean
    public ClientRegistrationRepository clientRepository() {
        ClientRegistration clientReg = clientRegistration();
        return new InMemoryClientRegistrationRepository(clientReg);
    }

}
