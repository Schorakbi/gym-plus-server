package com.smz.gymPlus.services;

import com.smz.gymPlus.models.Role;
import com.smz.gymPlus.models.Status;
import com.smz.gymPlus.models.User;
import com.smz.gymPlus.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }


    public User saveUser(User newUser) {

        if(newUser.getId() == null) {
            newUser.setCreatedAt(LocalDateTime.now());
        }

        newUser.setLastUpdatedAt(LocalDateTime.now());

        return userRepository.save(newUser);
    }
    public boolean isUserExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }

    public void logOut(User user) {

        var disconnectingUser = userRepository.findByUsername(user.getUsername())
                .orElse(null);

        if(disconnectingUser != null ) {
            disconnectingUser.setStatus(Status.OFFLINE);
          userRepository.save(disconnectingUser);
        }

    }
    public void logIn(User user) {

        var connectingUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + user.getUsername() + " not found"));

        if(connectingUser != null ) {
            connectingUser.setStatus(Status.ONLINE);
            userRepository.save(connectingUser);
        }

    }

    public List<User> findConnectedUsers() {
        return userRepository.findAllByStatus(Status.ONLINE);
    }

}
