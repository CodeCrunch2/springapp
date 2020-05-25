package com.mkudryavtsev.springapp.mapper;

import com.mkudryavtsev.springapp.dto.DeveloperDto;
import com.mkudryavtsev.springapp.model.Developer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DeveloperMapper {
    private final ModelMapper modelMapper;


    public DeveloperMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public Developer toEntity(DeveloperDto developerDto) {
        return Objects.isNull(developerDto) ? null : modelMapper.map(developerDto, Developer.class);
    }

    public DeveloperDto toDto(Developer developer) {

        return Objects.isNull(developer) ? null : modelMapper.map(developer, DeveloperDto.class);
    }
}
