package com.smz.gymPlus.dtos;

import com.smz.gymPlus.models.Role;
import com.smz.gymPlus.models.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserDto {

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private Set<Role> roles;
    private Status status;

}
