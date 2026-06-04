package com.xtraseats.service;
import com.xtraseats.dto.ContactResponse;
import com.xtraseats.dto.*;
import com.xtraseats.entity.MovieTicket;
import com.xtraseats.entity.User;
import com.xtraseats.repository.MovieTicketRepository;
import com.xtraseats.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieTicketService {

    @Autowired
    private MovieTicketRepository ticketRepo;

    @Autowired
    private UserRepository userRepo;

    // ── Private helper: Entity → Response DTO (no contact) ──
    private MovieTicketResponse toResponse(MovieTicket t) {
        MovieTicketResponse r = new MovieTicketResponse();
        r.setId(t.getId());
        r.setMovieName(t.getMovieName());
        r.setTheatreName(t.getTheatreName());
        r.setLanguage(t.getLanguage());
        r.setShowDate(t.getShowDate());
        r.setShowTime(t.getShowTime());
        r.setSeatNumbers(t.getSeatNumbers());
        r.setTicketCount(t.getTicketCount());
        r.setPricePerSeat(t.getPricePerSeat());
        r.setScreenFormat(t.getScreenFormat());
        r.setScreenNumber(t.getScreenNumber());
        r.setExtraInfo(t.getExtraInfo());
        r.setSellerName(t.getSellerName());
        r.setSellerNote(t.getSellerNote());
        r.setPostedAt(t.getPostedAt());
        // ⚠ sellerContact intentionally NOT mapped
        return r;
    }

    // ── GET /api/tickets ─────────────────────────────────────
    public List<MovieTicketResponse> getAllTickets() {
        return ticketRepo.findByActiveTrue()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // ── GET /api/tickets/{id} ────────────────────────────────
    public MovieTicketResponse getTicketById(Long id) {
        MovieTicket t = ticketRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found: " + id));
        return toResponse(t);
    }

    // ── POST /api/tickets ────────────────────────────────────
    public MovieTicketResponse postTicket(MovieTicketRequest req) {
        MovieTicket t = new MovieTicket();
        t.setMovieName(req.getMovieName());
        t.setTheatreName(req.getTheatreName());
        t.setLanguage(req.getLanguage());
        t.setShowDate(req.getShowDate());
        t.setShowTime(req.getShowTime());
        t.setSeatNumbers(req.getSeatNumbers());
        t.setTicketCount(req.getTicketCount());
        t.setPricePerSeat(req.getPricePerSeat());
        t.setScreenFormat(req.getScreenFormat());
        t.setScreenNumber(req.getScreenNumber());
        t.setExtraInfo(req.getExtraInfo());
        t.setSellerName(req.getSellerName());
        t.setSellerContact(req.getSellerContact()); // safely stored in DB
        t.setSellerNote(req.getSellerNote());
        t.setActive(true);
        return toResponse(ticketRepo.save(t));
    }

    // ── GET /api/tickets/{id}/contact ────────────────────────
    // ⭐ Core feature: subscription gate
    public ContactResponse getContact(Long ticketId, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        if (!user.isSubscribed()) {
            throw new RuntimeException(
                "NOT_SUBSCRIBED: Pay Rs.30 to unlock all seller contacts."
            );
        }

        MovieTicket t = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found: " + ticketId));

        return new ContactResponse(
                t.getSellerName(),
                t.getSellerContact(),
                "Contact unlocked successfully!"
        );
    }
}