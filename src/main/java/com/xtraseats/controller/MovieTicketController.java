package com.xtraseats.controller;

import com.xtraseats.dto.*;
import com.xtraseats.service.MovieTicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class MovieTicketController {

    private static final Logger log = LoggerFactory.getLogger(MovieTicketController.class);
    private final MovieTicketService ticketService;

    // city is optional — /api/tickets returns everything, /api/tickets?city=Chennai filters
    @GetMapping
    public ResponseEntity<List<MovieTicketResponse>> getAllTickets(
            @RequestParam(required = false) String city) {
        log.debug("GET /api/tickets — city filter: {}", city);
        if (city != null && !city.isBlank()) {
            return ResponseEntity.ok(ticketService.getTicketsByCity(city));
        }
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieTicketResponse> getTicketById(@PathVariable Long id) {
        log.debug("GET /api/tickets/{}", id);
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @PostMapping
    public ResponseEntity<MovieTicketResponse> postTicket(@Valid @RequestBody MovieTicketRequest request) {
        log.debug("POST /api/tickets — movie: {}", request.getMovieName());
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.postTicket(request));
    }

    @GetMapping("/{id}/contact")
    public ResponseEntity<ContactResponse> getContact(@PathVariable Long id, @RequestParam Long userId) {
        log.debug("GET /api/tickets/{}/contact — userId: {}", id, userId);
        return ResponseEntity.ok(ticketService.getContact(id, userId));
    }
}