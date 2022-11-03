package com.b2c.Local.B2C.auths.service;

import com.b2c.Local.B2C.auths.dao.UserRepository;
import com.b2c.Local.B2C.auths.dto.LoginDto;
import com.b2c.Local.B2C.auths.dto.UserDto;
import com.b2c.Local.B2C.auths.model.User;
import com.b2c.Local.B2C.exception.Conflict409Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService implements UserDetailsService {


    UserRepository userRepository;

    AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null){
            throw new UsernameNotFoundException("User with " +username+ " does not exits");
        }
        return user;
    }

    public User addUser(UserDto userDto){
        User user = new User();
        if (userDto.getEmail() != null){
            if (!userRepository.existsByEmail(userDto.getEmail())){
                user.setFirstName(userDto.getFirstName());
                user.setEmail(userDto.getEmail());
                user.setLastName(userDto.getLastName());
                user.setMobileNo(userDto.getMobileNumber());
                user.setStoreOwner(userDto.isStoreOwner());
                LocalDateTime localDateTime = LocalDateTime.now();
                user.setCreateDate(localDateTime);
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                if (userDto.getNewPassword().equals(userDto.getConfirmPassword())){
                    user.setPassword(bCryptPasswordEncoder.encode(userDto.getNewPassword() + localDateTime.toString()));
                } else {
                    throw new Conflict409Exception("New password and confirm password is not match!");
                }
                userRepository.save(user);
            }
            else {
                throw new Conflict409Exception("Email already exists!");
            }
        }
        return user;
    }

    public String login(LoginDto loginDto){
        if (loginDto.getEmail() != null && loginDto.getPassword() != null){
            if (userRepository.existsByEmail(loginDto.getEmail())) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.getEmail(),
                                loginDto.getPassword()
                        )
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else throw new BadCredentialsException("Invalid email!");
        }
        else throw new BadCredentialsException("Invalid email and password!");
        return "Login Successfully!";
    }
}
