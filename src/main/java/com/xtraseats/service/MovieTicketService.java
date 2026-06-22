package com.xtraseats.service;

import com.xtraseats.dto.*;
import com.xtraseats.entity.MovieTicket;
import com.xtraseats.entity.User;
import com.xtraseats.exception.NotSubscribedException;
import com.xtraseats.exception.TicketNotFoundException;
import com.xtraseats.exception.UserNotFoundException;
import com.xtraseats.repository.MovieTicketRepository;
import com.xtraseats.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieTicketService {

    private static final Logger log = LoggerFactory.getLogger(MovieTicketService.class);

    private final MovieTicketRepository ticketRepo;
    private final UserRepository userRepo;

    private MovieTicketResponse toResponse(MovieTicket t) {
        MovieTicketResponse r = new MovieTicketResponse();
        r.setId(t.getId());
        r.setMovieName(t.getMovieName());
        r.setTheatreName(t.getTheatreName());
        r.setCity(t.getCity());                 // ★ NEW
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
        return r;
    }

    public List<MovieTicketResponse> getAllTickets() {
        log.info("Fetching all active tickets");
        return ticketRepo.findByActiveTrue().stream().map(this::toResponse).collect(Collectors.toList());
    }

    // ★ NEW
    public List<MovieTicketResponse> getTicketsByCity(String city) {
        log.info("Fetching tickets for city: {}", city);
        return ticketRepo.findByCityAndActiveTrue(city).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public MovieTicketResponse getTicketById(Long id) {
        log.info("Fetching ticket id: {}", id);
        MovieTicket t = ticketRepo.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
        return toResponse(t);
    }

    public MovieTicketResponse postTicket(MovieTicketRequest req) {
        log.info("Posting ticket — movie: '{}', city: '{}', theatre: '{}'",
                req.getMovieName(), req.getCity(), req.getTheatreName());

        MovieTicket t = new MovieTicket();
        t.setMovieName(req.getMovieName());
        t.setTheatreName(req.getTheatreName());
        t.setCity(req.getCity());               // ★ NEW
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
        t.setSellerContact(req.getSellerContact());
        t.setSellerNote(req.getSellerNote());
        t.setActive(true);

        MovieTicket saved = ticketRepo.save(t);
        log.info("Ticket posted successfully — id: {}", saved.getId());
        return toResponse(saved);
    }

    public ContactResponse getContact(Long ticketId, Long userId) {
        log.info("Contact request — ticket: {}, user: {}", ticketId, userId);

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (!user.isSubscribed()) {
            log.warn("Unsubscribed user {} attempted contact for ticket {}", userId, ticketId);
            throw new NotSubscribedException(userId);
        }

        MovieTicket t = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException(ticketId));

        log.info("Contact revealed — ticket: {} to user: {}", ticketId, userId);
        return new ContactResponse(t.getSellerName(), t.getSellerContact(), "Contact unlocked successfully!");
    }
}