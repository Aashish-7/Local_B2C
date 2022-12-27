package com.b2c.Local.B2C;

import com.b2c.Local.B2C.auths.dao.UserRepository;
import com.b2c.Local.B2C.auths.enums.Role;
import com.b2c.Local.B2C.auths.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class Bootstrap implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public Bootstrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        addUser();
    }

    private void addUser() {
        if (userRepository.count() == 0) {
            log.info("Enter in bootstrap");
            User user = new User();
            user.setEmail("rishabh@gmail.com");
            user.setRole(Role.ADMIN);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode("rishabh@gmail.com"));
            userRepository.save(user);

            User user1 = new User();
            user1.setEmail("aashish@gmail.com");
            user1.setRole(Role.ADMIN);
            user1.setPassword(bCryptPasswordEncoder.encode("aashish@gmail.com"));
            userRepository.save(user1);
            log.info("Exit in bootstrap");
        }else {
            log.warn("Bootstrap run but user repository is not null");
        }
    }
}
