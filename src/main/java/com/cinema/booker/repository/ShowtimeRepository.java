package com.cinema.booker.repository;

import com.cinema.booker.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    @Query(value = "SELECT * FROM showtime " +
            "WHERE movie_id = :movieId " +
            "AND DATE(show_time) = :localDate",
            nativeQuery = true)
    List<Showtime> getShowtimesByMovieAndDate(
            @Param("movieId") long movieId,
            @Param("localDate") LocalDate localDate);

    List<Showtime> getShowtimesByMovieId(long movieId);

    List<Showtime> findByTheatreId(long theatreId);
}
