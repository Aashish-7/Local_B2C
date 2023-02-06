package com.b2c.Local.B2C.auths;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstPage {

    @GetMapping("/")
    public String indexPage(){
        return "Welcome to Local_B2C \n" +
                "For Oauth2 Login -> /oauth2/authorization/github";
    }
}
