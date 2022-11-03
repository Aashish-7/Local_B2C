package com.b2c.Local.B2C.auths.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String newPassword;

    private String confirmPassword;

    private String email;

    private String firstName;

    private String lastName;

    private String mobileNumber;
}
