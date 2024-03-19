package com.smz.gymPlus.configs;

import com.smz.gymPlus.models.Role;
import com.smz.gymPlus.models.Status;
import com.smz.gymPlus.models.User;
import com.smz.gymPlus.repositories.UserRepository;
import com.smz.gymPlus.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {
            Set<Role> roles = new HashSet<>();
            roles.add(Role.ROLE_ADMIN);
            roles.add(Role.ROLE_USER);

            User admin = User
                    .builder()
                    .firstname("admin")
                    .lastname("admin")
                    .email("admin@admin.com")
                    .username("admin")
                    .password(passwordEncoder.encode("password"))
                    .roles(roles)
                    .status(Status.OFFLINE)
                    .build();


            userService.saveUser(admin);
            log.debug("created ADMIN user - {}", admin);
        }
    }

}
