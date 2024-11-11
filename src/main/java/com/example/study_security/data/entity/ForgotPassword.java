package com.example.study_security.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "forgot_password")
public class ForgotPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forgot_password_id", nullable = false)
    private Integer id;

    @OneToOne
    private Users user;

    @Column(name = "otp")
    private Integer otp;

    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @ColumnDefault("'0000-00-00 00:00:00'")
    @Column(name = "expiration_time", nullable = false)
    private Date expirationTime;

}