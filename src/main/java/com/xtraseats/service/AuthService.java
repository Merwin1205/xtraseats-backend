package com.xtraseats.service;

import com.xtraseats.dto.LoginRequest;
import com.xtraseats.dto.RegisterRequest;
import com.xtraseats.entity.User;
import com.xtraseats.exception.InvalidCredentialsException;
import com.xtraseats.exception.UsernameAlreadyExistsException;
import com.xtraseats.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder; // injected from PasswordConfig bean

    public User register(RegisterRequest req) {
        log.info("Register attempt — username: {}", req.getUsername());

        if (userRepo.existsByUsername(req.getUsername())) {
            log.warn("Registration blocked — username taken: {}", req.getUsername());
            throw new UsernameAlreadyExistsException(req.getUsername());
        }

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword())); // hash — never store plain text
        user.setName(req.getName());
        user.setSubscribed(false);

        User saved = userRepo.save(user);
        log.info("User registered — id: {}, username: {}", saved.getId(), saved.getUsername());
        return saved;
    }

    public User login(LoginRequest req) {
        log.info("Login attempt — username: {}", req.getUsername());

        User user = userRepo.findByUsername(req.getUsername())
                .orElseThrow(() -> {
                    log.warn("Login failed — no such username: {}", req.getUsername());
                    return new InvalidCredentialsException();
                });

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            log.warn("Login failed — wrong password for username: {}", req.getUsername());
            throw new InvalidCredentialsException();
        }

        log.info("Login successful — username: {}", req.getUsername());
        return user;
    }
}