package com.xtraseats.controller;

import com.xtraseats.dto.PaymentRequest;
import com.xtraseats.entity.User;
import com.xtraseats.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    private final UserService userService;

    @PostMapping("/payment")
    public ResponseEntity<?> processPayment(@Valid @RequestBody PaymentRequest request) {
        log.debug("POST /api/payment — userId: {}, amount: {}", request.getUserId(), request.getAmount());

        // @Valid already guarantees amount is non-null by this point
        if (request.getAmount() != 30.0) {
            log.warn("Invalid payment amount: {}", request.getAmount());
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid amount. Rs.30 required."));
        }

        User user = userService.processPayment(request);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Payment successful! All seller contacts are now unlocked.",
                "userId", user.getId(),
                "isSubscribed", user.isSubscribed()
        ));
    }
}