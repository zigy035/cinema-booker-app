package com.cinema.booker.controller;

import com.cinema.booker.dto.CreateShowtimeListDto;
import com.cinema.booker.model.Showtime;
import com.cinema.booker.service.ShowtimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeMap;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/showtimes")
@RequiredArgsConstructor
@Tag(name = "Showtimes", description = "Endpoints for managing movie showtimes")
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    @GetMapping("/movie")
    @Operation(summary = "Get showtimes by movie and date", description = "Returns showtimes for a given movie on a specific date")
    public List<Showtime> getShowtimesByMovieAndDate(
            @RequestParam("movieId") long movieId,
            @RequestParam("localDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        return showtimeService.getShowtimesByMovieAndDate(movieId, localDate);
    }

    @GetMapping("/movie/{movieId}")
    @Operation(summary = "Get showtime slots", description = "Returns available time slots grouped by date for a given movie")
    public TreeMap<String, SortedSet<String>> getShowtimeTimeSlots(@PathVariable("movieId") long movieId) {
        return showtimeService.getShowtimeSlots(movieId);
    }

    @Operation(summary = "Create showtimes", description = "Creates one or more new showtimes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Showtimes created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "401", description = "Data not found"),
    })
    @PostMapping
    public ResponseEntity<List<Showtime>> createShowtimes(
            @Valid @RequestBody CreateShowtimeListDto showtimeListDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(showtimeService.createShowtimes(showtimeListDto));
    }
}
