package com.b2c.Local.B2C.auths.controller;

import com.b2c.Local.B2C.auths.dto.UserDto;
import com.b2c.Local.B2C.auths.service.ForgetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/forgetPassword")
public class ForgetPasswordController {

    ForgetPasswordService forgetPasswordService;

    @Autowired
    public ForgetPasswordController(ForgetPasswordService forgetPasswordService) {
        this.forgetPasswordService = forgetPasswordService;
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam String email, HttpServletRequest httpServletRequest){
        return forgetPasswordService.resetPassword(email, httpServletRequest);
    }

    @PostMapping("changePassword/{token}")
    public String changePassword(@PathVariable String token, @RequestBody @Valid  UserDto userDto , HttpServletRequest httpServletRequest) throws IOException {
        return forgetPasswordService.changePassword(token, userDto, httpServletRequest);
    }
}
