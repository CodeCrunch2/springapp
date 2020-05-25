package com.mkudryavtsev.springapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * DTO class for registration new user.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationUserDto {
    private String username;
    private String password;
    private String phoneNumber;
    private String verifyCode;

}
