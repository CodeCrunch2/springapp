package com.mkudryavtsev.springapp.rest;


import com.mkudryavtsev.springapp.dto.UserDto;
import com.mkudryavtsev.springapp.mapper.UserMapper;
import com.mkudryavtsev.springapp.model.User;
import com.mkudryavtsev.springapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserRestControllerV1(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto userDto = userMapper.toDto(user);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = this.userService.getAll();
        if(users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<UserDto> userDtos =
                users
                        .stream()
                        .map(userMapper::toDto)
                        .collect(Collectors.toList());
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
