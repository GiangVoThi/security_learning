package com.example.study_security.repository;

import com.example.study_security.data.dto.response.UsersDto;
import com.example.study_security.data.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    boolean existsByUsername(String username);
    List<Users> findAll();
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Users u set u.password = ?2 where u.email = ?1")
    void updatePassword(String email, String password);
}