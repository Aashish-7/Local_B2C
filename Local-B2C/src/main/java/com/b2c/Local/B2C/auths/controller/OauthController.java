package com.b2c.Local.B2C.auths.controller;

import com.b2c.Local.B2C.auths.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class OauthController {

    private AuthenticationProvider authenticationProvider;

    private OauthService oauthService;

    private OAuth2AuthorizedClientService service;

    private HttpServletResponse httpServletResponse;


    @Autowired
    public OauthController(OauthService oauthService, OAuth2AuthorizedClientService service, HttpServletResponse httpServletResponse) {
        this.oauthService = oauthService;
        this.service = service;
        this.httpServletResponse = httpServletResponse;
    }


    @GetMapping("/login/oauth2/code/github")
    public String getClientCode(@RequestParam String code) throws IOException {
        System.out.println(code);
        String githubUrl = "https://github.com/login/oauth/access_token?client_id=2c1ebc8fdc8c61aaaa09&client_secret=d3329bda9c0e69d55089294b5fe3a127816b53b1&code="+code;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(githubUrl,String.class);
        return response.toString();
    }

    @GetMapping("/login/oauth2")
    public void redirectUrlToGithub(HttpServletResponse httpServletResponse) throws IOException {
       // httpServletResponse.sendRedirect("https://github.com/login/oauth/authorize?client_id=2c1ebc8fdc8c61aaaa09&redirect_uri=http://localhost:8080/login/oauth2/code/github");
        httpServletResponse.sendRedirect("http://authserver:8000:/oauth2/authorize?response_type=code&client_id=messageClient&scope=read&redirect_uri=http://spring.io/&code_challenge=aGVsb3Nmc2dzb2Zoc2Zvcw&code_challenge_method=S256");
    }
}
