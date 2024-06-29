package com.cinema.booker.repository;

import com.cinema.booker.model.Broadcast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BroadcastRepository extends JpaRepository<Broadcast, Long> {

    /*
    @Query("SELECT u FROM User u WHERE u.status = :status and u.name = :name")
    User findUserByStatusAndNameNamedParams(
      @Param("status") Integer status,
      @Param("name") String name);
    */

    //SELECT * FROM broadcast b
    //         WHERE b.movie_id = 1 AND b.broadcast_time::date = NOW()::date
    @Query(value = "SELECT * FROM Broadcast b " +
            "WHERE b.movie_id = :movieId " +
            "AND DATE(b.broadcast_time) = :localDate", nativeQuery = true)
    List<Broadcast> getBroadcastsByMovieAndDate(
            @Param("movieId") long movieId,
            @Param("localDate") LocalDate localDate);

    List<Broadcast> getBroadcastsByMovieId(long movieId);
}
