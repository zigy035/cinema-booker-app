package com.cinema.booker.repository;

import com.cinema.booker.model.Showtime;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ShowtimeRepository extends PagingAndSortingRepository<Showtime, Long> {

    @Query(value = "SELECT * FROM showtime " +
            "WHERE movie_id = :movieId " +
            "AND DATE(show_time) = :localDate")
    List<Showtime> getShowtimesByMovieAndDate(
            @Param("movieId") long movieId,
            @Param("localDate") LocalDate localDate);

    List<Showtime> getShowtimesByMovieId(long movieId);
}
