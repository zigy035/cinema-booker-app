package com.cinema.booker.service;

import com.cinema.booker.model.Movie;
import com.cinema.booker.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Page<Movie> getAllMovies() {
        return movieRepository.findAll(Pageable.unpaged());
    }
}
