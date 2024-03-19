package com.smz.gymPlus.mappers;

import com.smz.gymPlus.dtos.SignInRequest;
import com.smz.gymPlus.dtos.SignUpRequest;
import com.smz.gymPlus.dtos.UserDto;
import com.smz.gymPlus.models.User;
import com.smz.gymPlus.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    UserService userService;

    public UserDto fromUserToUserDto(User user) {
        return UserDto.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .status(user.getStatus())
                .build();
    }

    public User fromUserDtoToUser(UserDto userDto) {
        return userService.findUserByUsername(userDto.getUsername());
    }

    public UserDto fromSignInRequestToUserDto(SignInRequest signInRequest) {

        User user =  userService.findUserByUsername(signInRequest.getUsername());
        return fromUserToUserDto(user);

    }
    public User fromSignUpRequestToUser(SignUpRequest signUpRequest) {
        return User.builder()
                .firstname(signUpRequest.getFirstname())
                .lastname(signUpRequest.getLastname())
                .email(signUpRequest.getEmail())
                .username(signUpRequest.getUsername())
                .password(signUpRequest.getPassword())
                .build();
    }

}
