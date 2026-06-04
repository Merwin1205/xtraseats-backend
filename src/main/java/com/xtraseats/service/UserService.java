package com.xtraseats.service;
import com.xtraseats.dto.PaymentRequest;
import com.xtraseats.dto.RegisterRequest;
import com.xtraseats.entity.User;
import com.xtraseats.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    // ── POST /api/users/register ─────────────────────────────
    public User register(RegisterRequest req) {
        if (userRepo.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered.");
        }
        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setSubscribed(false);
        return userRepo.save(user);
    }
    // ── GET /api/users/{id} ──────────────────────────────────
    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }
    // ── POST /api/payment ────────────────────────────────────
    // Mock payment — sets isSubscribed = true
    public User processPayment(PaymentRequest req) {
        // Find existing user by email, or create new
        User user = userRepo.findByEmail(req.getEmail()).orElse(null);

        if (user == null) {
            user = new User();
            user.setName(req.getName());
            user.setEmail(req.getEmail());
            user.setPhone(req.getPhone());
        }

        // Mock: no real payment — just mark subscribed
        user.setSubscribed(true);
        user.setSubscribedAt(LocalDateTime.now());
        return userRepo.save(user);
    }
}