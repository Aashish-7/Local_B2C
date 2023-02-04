package com.b2c.Local.B2C.auths.controller;

import com.b2c.Local.B2C.auths.dto.LoginDto;
import com.b2c.Local.B2C.auths.dto.UserDto;
import com.b2c.Local.B2C.auths.model.User;
import com.b2c.Local.B2C.auths.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@Validated
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public User checkUser(@RequestBody @Valid UserDto userDto, HttpServletRequest httpServletRequest) throws IOException {
        return userService.addUser(userDto, httpServletRequest);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody @Valid LoginDto loginDto) {
        return userService.loginUser(loginDto);
    }

    /*@PostMapping("/getToken")
    public Map<String, String> getToken(@RequestBody @Valid LoginDto loginDto){
        return userService.getToken(loginDto);
    }*/

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser() {
        return userService.deleteUser();
    }

    @GetMapping("/get")
    public User get() {
        return userService.get();
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam @NotBlank UUID id, @RequestBody @Valid UserDto userDto){
        return userService.changePassword(userDto,id);
    }
}
