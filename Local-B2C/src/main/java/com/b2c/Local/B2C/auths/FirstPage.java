package com.b2c.Local.B2C.auths;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Objects;

@RestController
public class FirstPage {

    @GetMapping("/")
    public String indexPage(Principal principal){
        if (Objects.nonNull(principal)){
            return "Hello "+ principal.getName()+" Welcome to Local_B2C";
        }
        return "Welcome to Local_B2C"+
                " For Oauth2 Login -> /oauth2/authorization/github";
    }
}
