package com.mkudryavtsev.springapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mkudryavtsev.springapp.model.Status;
import com.mkudryavtsev.springapp.model.User;
import lombok.Data;

/**
 * DTO class for {@link User} class.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;
    private String phoneNumber;
    private Status status;
}
