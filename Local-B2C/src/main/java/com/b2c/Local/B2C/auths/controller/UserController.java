package com.b2c.Local.B2C.auths.controller;

import com.b2c.Local.B2C.auths.dto.UserDto;
import com.b2c.Local.B2C.auths.model.User;
import com.b2c.Local.B2C.auths.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public User checkUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

//    @PostMapping("/login")
//    public String loginUser(@RequestBody LoginDto loginDto){
//        return userService.loginUser(loginDto);
//    }
}
