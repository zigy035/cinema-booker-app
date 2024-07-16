package com.cinema.booker.controller;

import com.cinema.booker.dto.CreateShowtimeDto;
import com.cinema.booker.model.Showtime;
import com.cinema.booker.service.ShowtimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeMap;

@RestController
@RequestMapping("/api/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    @GetMapping("/movie")
    public List<Showtime> getShowtimesByMovieAndDate(
            @RequestParam("movieId") long movieId,
            @RequestParam("localDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        return showtimeService.getShowtimesByMovieAndDate(movieId, localDate);
    }

    @GetMapping("/movie/{movieId}")
    public TreeMap<String, SortedSet<String>> getShowtimeTimeSlots(@PathVariable("movieId") long movieId) {
        return showtimeService.getShowtimeSlots(movieId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Showtime> createShowtimes(@Valid @RequestBody List<@Valid CreateShowtimeDto> showtimes) {
        return showtimeService.createShowtimes(showtimes);
    }
}
