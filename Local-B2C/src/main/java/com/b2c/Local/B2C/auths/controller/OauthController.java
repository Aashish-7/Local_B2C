package com.b2c.Local.B2C.auths.controller;

import com.b2c.Local.B2C.auths.service.OauthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class OauthController {

    private AuthenticationProvider authenticationProvider;

    private OAuth2AuthorizedClientService service;

    private HttpServletResponse httpServletResponse;

    private OauthUserService oauthUserService;

    @Autowired
    public OauthController(AuthenticationProvider authenticationProvider, OAuth2AuthorizedClientService service, HttpServletResponse httpServletResponse, OauthUserService oauthUserService) {
        this.authenticationProvider = authenticationProvider;
        this.service = service;
        this.httpServletResponse = httpServletResponse;
        this.oauthUserService = oauthUserService;
    }

    /*@GetMapping("/login/oauth2/code/github")
    public String getClientCode(@RequestParam String code , @RequestParam String state) throws IOException {
        System.out.println(code);
        String githubUrl = "https://github.com/login/oauth/access_token?client_id=&client_secret=&code="+code+"&state="+state;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(githubUrl,String.class);
        return response.getHeaders().toString();
    }
*/
   /* @GetMapping("/login/oauth2")
    public void redirectUrlToGithub(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("https://github.com/login/oauth/authorize?client_id=&scope=openid&redirect_uri=http://localhost:8080/login/oauth2/code/github");
    }*/
}
