package com.xtraseats.controller;

import com.xtraseats.dto.*;
import com.xtraseats.service.MovieTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:5173")
public class MovieTicketController {

    @Autowired
    private MovieTicketService ticketService;

    // GET /api/tickets — all listings, no contact
    @GetMapping
    public ResponseEntity<List<MovieTicketResponse>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    // GET /api/tickets/{id} — single ticket, no contact
    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ticketService.getTicketById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/tickets — seller posts a ticket
    @PostMapping
    public ResponseEntity<?> postTicket(@RequestBody MovieTicketRequest request) {
        try {
            return ResponseEntity.ok(ticketService.postTicket(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /api/tickets/{id}/contact?userId={userId}
    // ⭐ Subscription-gated — 403 if not subscribed
    @GetMapping("/{id}/contact")
    public ResponseEntity<?> getContact(
            @PathVariable Long id,
            @RequestParam Long userId) {
        try {
            ContactResponse contact = ticketService.getContact(id, userId);
            return ResponseEntity.ok(contact);
        } catch (RuntimeException e) {
            if (e.getMessage().startsWith("NOT_SUBSCRIBED")) {
                return ResponseEntity.status(403).body(e.getMessage());
            }
            return ResponseEntity.notFound().build();
        }
    }
}