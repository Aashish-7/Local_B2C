package com.b2c.Local.B2C.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class EmailService {


    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(@Lazy JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String UserMail, String url) throws MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom("");
        helper.setTo(UserMail);
        helper.setSubject("Forget Password");
        helper.setText(url, true);
        javaMailSender.send(msg);
    }


}
