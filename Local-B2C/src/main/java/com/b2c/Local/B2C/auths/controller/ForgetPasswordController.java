package com.b2c.Local.B2C.auths.controller;

import com.b2c.Local.B2C.auths.service.ForgetPasswordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forgetPassword")
public class ForgetPasswordController {

    ForgetPasswordService forgetPasswordService;

    public ForgetPasswordController(ForgetPasswordService forgetPasswordService) {
        this.forgetPasswordService = forgetPasswordService;
    }

    @PostMapping("/{email}/resetPassword")
    public String resetPassword(@PathVariable String email){
        return forgetPasswordService.resetPassword(email);
    }
}
