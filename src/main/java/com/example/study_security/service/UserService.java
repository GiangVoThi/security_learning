package com.example.study_security.service;

import com.example.study_security.data.dto.response.UsersDto;
import com.example.study_security.data.entity.Users;

import java.util.List;

public interface UserService {
    boolean existsByUsername(String username);
    List<UsersDto> findAllUsers();
}
