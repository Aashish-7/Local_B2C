package com.b2c.Local.B2C.auths.controller;

import com.b2c.Local.B2C.auths.service.UserMapStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/UserMapStore")
public class UserMapStoreController {

    UserMapStoreService userMapStoreService;

    @Autowired
    public UserMapStoreController(UserMapStoreService userMapStoreService) {
        this.userMapStoreService = userMapStoreService;
    }

}
