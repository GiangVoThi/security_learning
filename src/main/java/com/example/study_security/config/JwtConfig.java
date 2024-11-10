package com.example.study_security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class JwtConfig {
    @Value("${vault:secret/jwt-key/data.value}")
    private String rawSecretKey;

    @Bean
    public String jwtSecretKey() {
        // In ra giá trị để debug
        System.out.println("Raw secret from vault: " + rawSecretKey);

        // Loại bỏ các ký tự đặc biệt và khoảng trắng nếu có
        String cleanedKey = rawSecretKey.trim()
                .replaceAll("[\\n\\r\\t]", "")
                .replaceAll("\"", ""); // Loại bỏ dấu ngoặc kép nếu có

        System.out.println("Cleaned secret: " + cleanedKey);

        return cleanedKey;
    }
}
