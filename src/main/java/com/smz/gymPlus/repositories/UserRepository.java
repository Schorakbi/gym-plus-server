package com.smz.gymPlus.repositories;

import com.smz.gymPlus.models.Status;
import com.smz.gymPlus.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    List<User> findAllByStatus(Status status);
}
