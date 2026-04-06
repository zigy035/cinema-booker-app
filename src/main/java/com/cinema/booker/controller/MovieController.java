package com.cinema.booker.controller;

import com.cinema.booker.model.Movie;
import com.cinema.booker.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@Tag(name = "Movies", description = "Endpoints for browsing movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    @Operation(summary = "Get all movies", description = "Returns a paginated list of all movies")
    public Page<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get movie by ID", description = "Returns a single movie by its ID")
    public Optional<Movie> getMovie(@PathVariable("id") long id) {
        return movieService.getMovie(id);
    }
}
