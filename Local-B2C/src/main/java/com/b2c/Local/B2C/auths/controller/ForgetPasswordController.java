package com.b2c.Local.B2C.auths.controller;

import com.b2c.Local.B2C.auths.dto.UserDto;
import com.b2c.Local.B2C.auths.service.ForgetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.security.Principal;

@RestController
@Validated
@RequestMapping("/forgetPassword")
public class ForgetPasswordController {

    ForgetPasswordService forgetPasswordService;

    @Autowired
    public ForgetPasswordController(ForgetPasswordService forgetPasswordService) {
        this.forgetPasswordService = forgetPasswordService;
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam @Email String email, @NotNull HttpServletRequest httpServletRequest,@Null Principal authentication){
        return forgetPasswordService.resetPassword(email, httpServletRequest);
    }

    @PostMapping("changePassword/{token}")
    public String changePassword(@PathVariable @Size(min = 5) String token, @RequestBody @Valid UserDto userDto , @NotNull HttpServletRequest httpServletRequest,@Null Principal authentication) throws IOException {
        return forgetPasswordService.changePassword(token, userDto, httpServletRequest);
    }
}
