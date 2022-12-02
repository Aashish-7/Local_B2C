package com.b2c.Local.B2C.auths.service;

import com.b2c.Local.B2C.auths.dao.ForgetPasswordRepository;
import com.b2c.Local.B2C.auths.dao.UserRepository;
import com.b2c.Local.B2C.auths.dto.UserDto;
import com.b2c.Local.B2C.auths.model.ForgetPassword;
import com.b2c.Local.B2C.auths.model.User;
import com.b2c.Local.B2C.exception.BadRequest400Exception;
import com.b2c.Local.B2C.exception.Conflict409Exception;
import com.b2c.Local.B2C.exception.Forbidden403Exception;
import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.utility.EmailService;
import com.b2c.Local.B2C.utility.RandomString;
import com.b2c.Local.B2C.utility.UserMacAddress;
import com.itextpdf.text.exceptions.BadPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@Service
public class ForgetPasswordService {

    ForgetPasswordRepository forgetPasswordRepository;

    UserRepository userRepository;

    EmailService emailService;

    UserMacAddress userMacAddress;

    @Autowired
    public ForgetPasswordService(ForgetPasswordRepository forgetPasswordRepository, UserRepository userRepository, EmailService emailService, UserMacAddress userMacAddress) {
        this.forgetPasswordRepository = forgetPasswordRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.userMacAddress = userMacAddress;
    }

    public String resetPassword(String email, HttpServletRequest httpServletRequest) {
        if (forgetPasswordRepository.existsByActiveTrueAndUser_Email(email)) {
            throw new Conflict409Exception("One Token Already Exits");
        }
        if (userRepository.existsByEmailAndIsActiveTrue(email)) {
            try {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                ForgetPassword forgetPassword = new ForgetPassword();
                String firstToken = RandomString.getRandomPassword(20);
                forgetPassword.setToken(firstToken);
                forgetPassword.setActive(true);
                forgetPassword.setUserAgent(bCryptPasswordEncoder.encode(httpServletRequest.getHeader("User-Agent")));
                forgetPassword.setAddress(bCryptPasswordEncoder.encode(userMacAddress.arpByRemoteIp(httpServletRequest.getRemoteAddr())));
                forgetPassword.setSendTime(LocalDateTime.now());
                String redirectUrl = httpServletRequest.getServerName() + ":8080/forgetPassword/changePassword/" + firstToken;
                //emailService.sendEmail(email, redirectUrl);
                forgetPassword.setUser(userRepository.findByEmail(email));
                forgetPasswordRepository.save(forgetPassword);
                deleteTokenTaskActivate(forgetPassword.getId());
            } catch ( IOException e) {
                e.printStackTrace();
            }
            return "Check Your Inbox";
        } else {
            throw new NotFound404Exception("Enter Valid Email");
        }
    }

    public String changePassword(String token, UserDto userDto, HttpServletRequest httpServletRequest) throws IOException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!forgetPasswordRepository.existsByTokenAndActiveTrue(token)) {
            throw new NotFound404Exception("Token Not Valid");
        }
        ForgetPassword forgetPasswordToken = forgetPasswordRepository.findByTokenAndActiveTrue(token);
        if (!bCryptPasswordEncoder.matches(httpServletRequest.getHeader("User-Agent"), forgetPasswordToken.getUserAgent())) {
            throw new BadRequest400Exception("You Change Your Browser");
        }
        if (!bCryptPasswordEncoder.matches(userMacAddress.arpByRemoteIp(httpServletRequest.getRemoteAddr()), forgetPasswordToken.getAddress())) {
            throw new Forbidden403Exception("Your Device Id Change");
        }
        if (userDto.getNewPassword().equals(userDto.getConfirmPassword())) {
            ForgetPassword forgetPassword = forgetPasswordRepository.findByTokenAndActiveTrue(token);
            User user = userRepository.findByEmail(forgetPassword.getUser().getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getNewPassword()));
            userRepository.save(user);
            forgetPassword.setActive(false);
            forgetPasswordRepository.save(forgetPassword);
            return "Password Changed";
        } else {
            throw new BadPasswordException("Password Not Matches");
        }
    }

    public void deleteTokenTaskActivate(UUID uuid){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (forgetPasswordRepository.findById(uuid).isPresent()) {
                    ForgetPassword forgetPassword = forgetPasswordRepository.findById(uuid).get();
                    forgetPassword.setActive(false);
                    forgetPasswordRepository.save(forgetPassword);
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,600000);
    }

}
