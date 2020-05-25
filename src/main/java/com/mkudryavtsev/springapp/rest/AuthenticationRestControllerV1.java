package com.mkudryavtsev.springapp.rest;

import com.mkudryavtsev.springapp.dto.AuthenticationRequestDto;
import com.mkudryavtsev.springapp.dto.RegistrationUserDto;
import com.mkudryavtsev.springapp.mapper.RegistrationUserMapper;
import com.mkudryavtsev.springapp.model.User;
import com.mkudryavtsev.springapp.security.jwt.JwtTokenProvider;
import com.mkudryavtsev.springapp.security.twilio.TwilioVerifyService;
import com.mkudryavtsev.springapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for authentication requests.
 */

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final RegistrationUserMapper registrationUserMapper;

    private final TwilioVerifyService twilioVerifyService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager,
                                          JwtTokenProvider jwtTokenProvider,
                                          UserService userService,
                                          RegistrationUserMapper registrationUserMapper,
                                          TwilioVerifyService twilioVerifyService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.registrationUserMapper = registrationUserMapper;
        this.twilioVerifyService = twilioVerifyService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
    @PostMapping("register")
    public ResponseEntity<RegistrationUserDto> startRegistration(@RequestBody RegistrationUserDto userDto) {
        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User existUser = userService.findByUsername(userDto.getUsername());
        if (existUser != null) {
            throw new BadCredentialsException("User with the same username exists.");
        }
        twilioVerifyService.startVerification(userDto.getPhoneNumber());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("register/confirm")
    public ResponseEntity<RegistrationUserDto> confirmRegistration(@RequestBody RegistrationUserDto userDto) {
        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String verificationStatus =
                twilioVerifyService.checkVerification(userDto.getPhoneNumber(), userDto.getVerifyCode());
        if (!verificationStatus.equalsIgnoreCase("approved")) {
            throw new BadCredentialsException("Invalid verification code.");
        }

        User user = registrationUserMapper.toEntity(userDto);
        userService.register(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
