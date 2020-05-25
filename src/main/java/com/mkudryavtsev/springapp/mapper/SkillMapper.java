package com.mkudryavtsev.springapp.mapper;

import com.mkudryavtsev.springapp.dto.SkillDto;
import com.mkudryavtsev.springapp.model.Skill;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class SkillMapper {
    private final ModelMapper modelMapper;

    public SkillMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public Skill toEntity(SkillDto skillDto) {
        return Objects.isNull(skillDto) ? null : modelMapper.map(skillDto, Skill.class);
    }

    public SkillDto toDto(Skill skill) {

        return Objects.isNull(skill) ? null : modelMapper.map(skill, SkillDto.class);
    }
}
