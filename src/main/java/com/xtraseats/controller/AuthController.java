package com.xtraseats.controller;

import com.xtraseats.dto.LoginRequest;
import com.xtraseats.dto.RegisterRequest;
import com.xtraseats.entity.User;
import com.xtraseats.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "userId", user.getId(),
                "username", user.getUsername(),
                "name", user.getName(),
                "isSubscribed", user.isSubscribed(),
                "message", "Account created successfully!"
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequest request) {
        User user = authService.login(request);
        return ResponseEntity.ok(Map.of(
                "userId", user.getId(),
                "username", user.getUsername(),
                "name", user.getName(),
                "isSubscribed", user.isSubscribed(),
                "message", "Login successful!"
        ));
    }
}