package com.example.study_security.service.impl;

import com.example.study_security.data.dto.response.UsersDto;
import com.example.study_security.data.entity.Users;
import com.example.study_security.maptructs.UserConverter;
import com.example.study_security.repository.UsersRepository;
import com.example.study_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final UserConverter userConverter;

    @Override
    public boolean existsByUsername(String username) {
        return usersRepository.existsByUsername(username);
    }

    @Override
    public List<UsersDto> findAllUsers() {
        List<Users> users = usersRepository.findAll();
        return users.stream()
                .map(userConverter::toDto) // Giả sử `userConverter.toDto` chuyển đổi từ `Users` sang `UsersDto`
                .collect(Collectors.toList());
    }
}
