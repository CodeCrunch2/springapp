package com.mkudryavtsev.springapp.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mkudryavtsev.springapp.model.Developer;
import lombok.Data;

import java.util.Set;

/**
 * DTO class for {@link Developer} class.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeveloperDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String speciality;
    private Set<SkillDto> skills;
    private AccountDto account;
}
