package com.mkudryavtsev.springapp.mapper;

import com.mkudryavtsev.springapp.dto.RegistrationUserDto;
import com.mkudryavtsev.springapp.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RegistrationUserMapper {
    private final ModelMapper modelMapper;

    public RegistrationUserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public User toEntity(RegistrationUserDto registrationUserDto) {
        return Objects.isNull(registrationUserDto) ? null : modelMapper.map(registrationUserDto, User.class);
    }

    public RegistrationUserDto toDto(User user) {

        return Objects.isNull(user) ? null : modelMapper.map(user, RegistrationUserDto.class);
    }
}
