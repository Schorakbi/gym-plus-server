package com.smz.gymPlus.services;

import com.smz.gymPlus.dtos.JwtAuthenticationResponse;
import com.smz.gymPlus.dtos.SignInRequest;
import com.smz.gymPlus.dtos.SignUpRequest;
import com.smz.gymPlus.models.Role;
import com.smz.gymPlus.models.Status;
import com.smz.gymPlus.models.User;
import com.smz.gymPlus.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        var user = User
                .builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .status(Status.OFFLINE)
                .build();

        user = userService.saveUser(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
        var jwt = jwtService.generateToken(user);
        userService.logIn(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }



}
