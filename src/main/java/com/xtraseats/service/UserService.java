package com.xtraseats.service;

import com.xtraseats.dto.PaymentRequest;
import com.xtraseats.entity.User;
import com.xtraseats.exception.UserNotFoundException;
import com.xtraseats.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepo;

    public User getUserById(Long id) {
        log.info("Fetching user — id: {}", id);
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    // User must already be registered & logged in — payment just flips the flag
    public User processPayment(PaymentRequest req) {
        log.info("Processing Rs.30 payment — userId: {}", req.getUserId());

        User user = userRepo.findById(req.getUserId())
                .orElseThrow(() -> {
                    log.warn("Payment failed — user not found: {}", req.getUserId());
                    return new UserNotFoundException(req.getUserId());
                });

        user.setSubscribed(true);
        user.setSubscribedAt(LocalDateTime.now());

        User saved = userRepo.save(user);
        log.info("Payment complete — user id: {} is now subscribed", saved.getId());
        return saved;
    }
}