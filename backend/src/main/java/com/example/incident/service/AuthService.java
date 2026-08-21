package com.example.incident.service;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.incident.dto.*;
import com.example.incident.entity.*;
import com.example.incident.exception.BusinessException;
import com.example.incident.repository.*;
import com.example.incident.security.JwtService;

@Service @RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail()))
            throw new BusinessException("Email already registered");
        if (userRepository.existsByUsername(req.getUsername()))
            throw new BusinessException("Username already registered");

        User user = User.builder()
                .username(req.getUsername()).email(req.getEmail())
                .phone(req.getPhone()).address(req.getAddress())
                .pincode(req.getPincode()).city(req.getCity())
                .country(req.getCountry())
                .password(passwordEncoder.encode(req.getPassword()))
                .build();
        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        User user = userRepository.findByEmail(req.getEmail()).orElseThrow();
        return new LoginResponse(jwtService.generateToken(user.getEmail()),
                user.getUsername(), user.getEmail());
    }

    public String forgotPassword(ForgotPasswordRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new BusinessException("Email is not registered"));

        tokenRepository.findByUserId(user.getId()).ifPresent(tokenRepository::delete);
        String token = UUID.randomUUID().toString();
        tokenRepository.save(PasswordResetToken.builder()
                .token(token).user(user)
                .expiryTime(LocalDateTime.now().plusMinutes(30))
                .build());

        // Assignment/demo behavior: return token so reset can be demonstrated without SMTP.
        return token;
    }

    public void resetPassword(ResetPasswordRequest req) {
        PasswordResetToken token = tokenRepository.findByToken(req.getToken())
                .orElseThrow(() -> new BusinessException("Invalid reset token"));
        if (token.getExpiryTime().isBefore(LocalDateTime.now()))
            throw new BusinessException("Reset token has expired");

        User user = token.getUser();
        user.setPassword(passwordEncoder.encode(req.getNewPassword()));
        userRepository.save(user);
        tokenRepository.delete(token);
    }
}
