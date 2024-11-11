package com.example.study_security.api;

import com.example.study_security.data.dto.request.ChangePassword;
import com.example.study_security.data.dto.request.MailBody;
import com.example.study_security.data.entity.ForgotPassword;
import com.example.study_security.data.entity.Users;
import com.example.study_security.repository.ForgotPasswordRepository;
import com.example.study_security.repository.UsersRepository;
import com.example.study_security.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import java.time.Instant;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/forgot-password")
@RequiredArgsConstructor
public class ForgotPasswordApi {
    private final UsersRepository usersRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/verify-mail/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email) {

        // Kiểm tra định dạng email
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        // Log email để kiểm tra
        System.out.println("Email to be sent: " + email);

        // Tìm user dựa trên email
        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Please provide a valid email"));

        // Tạo OTP
        int otp = otpGenerator();
        MailBody mailBody = MailBody.builder()
                .to(email)
                .text("This is the OTP for your password reset: " + otp)
                .subject("OTP for forgot password request")
                .build();

        // Tạo đối tượng ForgotPassword
        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 30_000))
                .createdAt(new Date())
                .user(users)
                .build();

        try {
            // Gửi email
            emailService.sendSimpleMessage(mailBody);
        } catch (MailException e) {
            System.err.println("Error sending email: " + e.getMessage());
            e.printStackTrace(); // Ghi chi tiết stacktrace cho dễ kiểm tra
            throw new RuntimeException("Failed to send email");
        }

        // Lưu thông tin OTP vào cơ sở dữ liệu
        forgotPasswordRepository.save(fp);

        return ResponseEntity.ok("Mail sent successfully");
    }

    // Hàm kiểm tra định dạng email
    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(emailRegex);
    }

    @PostMapping("/verify-otp/{otp}/{email}")
    public ResponseEntity<String> verfiOtp(@PathVariable Integer otp, @PathVariable String email) {
        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("please provide a valid email"));
        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp, users)
                .orElseThrow(() -> new RuntimeException("invalid otp for email" + email));

        if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
            forgotPasswordRepository.deleteById(fp.getId());
            return new ResponseEntity<>("otp expired", HttpStatus.EXPECTATION_FAILED);
        }

        return ResponseEntity.ok("otp verified successfully");

    }

    @PostMapping("/change-password/{email}")
    public ResponseEntity<String> changePasswordHandler(
            @RequestBody ChangePassword changePassword,
            @PathVariable String email) {
        if (!Objects.equals(changePassword.password(), changePassword.repeatPassword())) {
            return new ResponseEntity<>("please enter the password again", HttpStatus.EXPECTATION_FAILED);
        }

        String encodedPassword = passwordEncoder.encode(changePassword.password());
        usersRepository.updatePassword(email, encodedPassword);

        return ResponseEntity.ok("password changed successfully");

    }

    private Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }
}
