package com.example.study_security.maptructs;


import com.example.study_security.data.dto.response.UsersDto;
import com.example.study_security.data.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConverter {
    Users toEntity(UsersDto usersDto);
    UsersDto toDto(Users users);
}
