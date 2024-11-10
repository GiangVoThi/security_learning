package com.example.study_security.repository;

import com.example.study_security.data.dto.response.UsersDto;
import com.example.study_security.data.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    boolean existsByUsername(String username);
    List<Users> findAll();
    Optional<Users> findByUsername(String username);
}