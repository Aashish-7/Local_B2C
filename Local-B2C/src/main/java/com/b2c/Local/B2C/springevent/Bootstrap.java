package com.b2c.Local.B2C.springevent;

import com.b2c.Local.B2C.auths.dao.UserRepository;
import com.b2c.Local.B2C.auths.dao.UserSecurityDetailsRepository;
import com.b2c.Local.B2C.auths.enums.Role;
import com.b2c.Local.B2C.auths.model.User;
import com.b2c.Local.B2C.auths.model.UserSecurityDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Log4j2
public class Bootstrap implements CommandLineRunner {

    private final UserRepository userRepository;

    private final UserSecurityDetailsRepository userSecurityDetailsRepository;

    @Autowired
    public Bootstrap(UserRepository userRepository,UserSecurityDetailsRepository userSecurityDetailsRepository) {
        this.userRepository = userRepository;
        this.userSecurityDetailsRepository = userSecurityDetailsRepository;
    }

    @Override
    public void run(String... args) {
        log.info("Local_B2C Bootstrap Started...");
        addUser();
    }

    private void addUser() {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setCreateDate(LocalDateTime.now());
            user.setEmail("rishabh@gmail.com");
            user.setStoreOwner(false);
            user.setFirstName("Rishabh");
            user.setLastName("Maurya");
            user.setMobileNo(9999999999L);
            user.setUsername("rishabh@gmail.com");
            user.setRole(Role.ADMIN);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode("rishabh@gmail.com"));
            userRepository.save(user);
            UserSecurityDetails rishabhSecurityDetails = new UserSecurityDetails();
            rishabhSecurityDetails.setUser(user);
            rishabhSecurityDetails.setMaxSession(1);
            rishabhSecurityDetails.setMacAddress(null);
            userSecurityDetailsRepository.save(rishabhSecurityDetails);

            User user1 = new User();
            user1.setCreateDate(LocalDateTime.now());
            user1.setEmail("aashish@gmail.com");
            user1.setStoreOwner(false);
            user1.setFirstName("Aashish");
            user1.setLastName("Prajapati");
            user1.setMobileNo(9999999999L);
            user1.setUsername("aashish@gmail.com");
            user1.setRole(Role.ADMIN);
            user1.setPassword(bCryptPasswordEncoder.encode("aashish@gmail.com"));
            userRepository.save(user1);
            UserSecurityDetails aashishSecurityDetails = new UserSecurityDetails();
            aashishSecurityDetails.setUser(user1);
            aashishSecurityDetails.setMaxSession(1);
            aashishSecurityDetails.setMacAddress(null);
            userSecurityDetailsRepository.save(aashishSecurityDetails);
        }
    }
}
