package com.smz.gymPlus.controllers;

import com.smz.gymPlus.dtos.JwtAuthenticationResponse;
import com.smz.gymPlus.dtos.SignInRequest;
import com.smz.gymPlus.dtos.SignUpRequest;
import com.smz.gymPlus.dtos.UserDto;
import com.smz.gymPlus.mappers.UserMapper;
import com.smz.gymPlus.models.User;
import com.smz.gymPlus.services.AuthenticationService;
import com.smz.gymPlus.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping("/signup")
    public JwtAuthenticationResponse signUp(@RequestBody SignUpRequest request) {
        return authenticationService.signUp(request);
    }
    @MessageMapping("/user.signin")
    @SendTo("/user/topic")
    @PostMapping("/signin")
    public JwtAuthenticationResponse signIn(@RequestBody @Payload SignInRequest request) {
        return authenticationService.signIn(request);
    }
    @MessageMapping("/user.logout")
    @SendTo("/user/topic")
    @PostMapping("/logout")
    public UserDto logOut(
            @Payload UserDto userDto
    ) {
        User user = userMapper.fromUserDtoToUser(userDto);
        userService.logOut(user);
        return userDto;

    }


}