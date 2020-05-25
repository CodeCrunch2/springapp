package com.mkudryavtsev.springapp.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mkudryavtsev.springapp.model.Skill;
import lombok.Data;

/**
 * DTO class for {@link Skill} class.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkillDto {
    private Long id;
    private String name;
}
