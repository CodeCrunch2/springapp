package com.mkudryavtsev.springapp.mapper;

import com.mkudryavtsev.springapp.dto.UserDto;
import com.mkudryavtsev.springapp.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User toEntity(UserDto userDto) {
        return Objects.isNull(userDto) ? null : modelMapper.map(userDto, User.class);
    }

    public UserDto toDto(User user) {

        return Objects.isNull(user) ? null : modelMapper.map(user, UserDto.class);
    }
}
