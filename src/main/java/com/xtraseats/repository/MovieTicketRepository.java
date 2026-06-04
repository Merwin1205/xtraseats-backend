package com.xtraseats.repository;

import com.xtraseats.entity.MovieTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MovieTicketRepository extends JpaRepository<MovieTicket, Long> {

    // All active listings
    List<MovieTicket> findByActiveTrue();

    // Search by movie name (case-insensitive)
    List<MovieTicket> findByMovieNameContainingIgnoreCaseAndActiveTrue(String movieName);

    // Filter by language
    List<MovieTicket> findByLanguageAndActiveTrue(String language);

    // Filter by screen format (2D / 3D etc.)
    List<MovieTicket> findByScreenFormatAndActiveTrue(String screenFormat);

    // Filter by theatre
    List<MovieTicket> findByTheatreNameContainingIgnoreCaseAndActiveTrue(String theatreName);
}