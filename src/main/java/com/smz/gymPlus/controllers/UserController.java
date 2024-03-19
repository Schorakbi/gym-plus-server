package com.smz.gymPlus.controllers;

import com.smz.gymPlus.dtos.UserDto;
import com.smz.gymPlus.mappers.UserMapper;
import com.smz.gymPlus.models.User;
import com.smz.gymPlus.services.JwtService;
import com.smz.gymPlus.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/v1")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;
    public final UserMapper userMapper;
    private final JwtService jwtService;

    @GetMapping("/connected_users")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<User>> getConnectedUsers() {
        return ResponseEntity.ok(userService.findConnectedUsers());
    }
    @GetMapping("/api/v1/user")
    public UserDto getUserDetailsFromToken(@RequestHeader("Authorization") String jwt) {
        String token = jwt.substring(7);
        String username = jwtService.extractUsername(token);
        return userMapper.fromUserToUserDto(userService.findUserByUsername(username));

    }


}
