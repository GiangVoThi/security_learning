package com.example.study_security.data.dto.response;

import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.example.study_security.data.entity.Users}
 */
@Value
public class UsersDto implements Serializable {
    Integer userId;
    String username;
    String password;
    Set<RolesDto> roles;
}