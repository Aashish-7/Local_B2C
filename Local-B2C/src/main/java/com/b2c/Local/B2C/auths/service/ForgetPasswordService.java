package com.b2c.Local.B2C.auths.service;

import com.b2c.Local.B2C.auths.dao.ForgetPasswordRepository;
import com.b2c.Local.B2C.auths.dao.UserRepository;
import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.utility.EmailService;
import com.b2c.Local.B2C.utility.RandomString;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@Service
public class ForgetPasswordService {

    ForgetPasswordRepository forgetPasswordRepository;

    UserRepository userRepository;

    EmailService emailService;

    public ForgetPasswordService(ForgetPasswordRepository forgetPasswordRepository, UserRepository userRepository, EmailService emailService) {
        this.forgetPasswordRepository = forgetPasswordRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public String resetPassword(String email){
        if (userRepository.existsByEmailAndIsActiveTrue(email)){
            try {
                String firstToken = RandomString.getRandomPassword(12);
                String secondToken = RandomString.getRandom(5);
                String redirectUrl = ""+secondToken+"/changePassword/"+firstToken;
                emailService.sendEmail(email,redirectUrl);
            }catch (MessagingException | IOException e) {
                e.printStackTrace();
            }
            return "check your Inbox";
        }else {
            throw new NotFound404Exception("Enter Valid Email");
        }
    }

}
