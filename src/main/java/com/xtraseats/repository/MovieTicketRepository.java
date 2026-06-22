package com.xtraseats.repository;

import com.xtraseats.entity.MovieTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MovieTicketRepository extends JpaRepository<MovieTicket, Long> {

    List<MovieTicket> findByActiveTrue();

    // ★ NEW — powers the city filter
    List<MovieTicket> findByCityAndActiveTrue(String city);

    List<MovieTicket> findByMovieNameContainingIgnoreCaseAndActiveTrue(String movieName);
    List<MovieTicket> findByLanguageAndActiveTrue(String language);
    List<MovieTicket> findByScreenFormatAndActiveTrue(String screenFormat);
    List<MovieTicket> findByTheatreNameContainingIgnoreCaseAndActiveTrue(String theatreName);
}