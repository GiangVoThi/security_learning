package com.example.study_security.data.dto.response;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.study_security.data.entity.Roles}
 */
@Value
public class RolesDto implements Serializable {
    Integer roleId;
    String roleName;
}