package com.xtraseats.controller;

import com.xtraseats.dto.PaymentRequest;
import com.xtraseats.entity.User;
import com.xtraseats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {

    @Autowired
    private UserService userService;

    // POST /api/payment — mock Rs.30 payment
    @PostMapping("/payment")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest request) {
        try {
            if (request.getAmount() == null || request.getAmount() != 30.0) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Invalid amount. Rs.30 required."));
            }
            User user = userService.processPayment(request);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Payment successful! All seller contacts are now unlocked.",
                    "userId", user.getId(),
                    "isSubscribed", user.isSubscribed()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}