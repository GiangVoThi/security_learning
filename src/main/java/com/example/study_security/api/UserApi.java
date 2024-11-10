package com.example.study_security.api;

import com.example.study_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api-user")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @PostMapping("/exists-by-user-name")
    public boolean existsByUserName(@RequestParam("username") String username) {
        return userService.existsByUsername(username);
    }

    @GetMapping("/find-all-users")
    public ResponseEntity<?> findAllUsers() {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("message", "Call api success");
            result.put("data", userService.findAllUsers());
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Call api fail");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
}

