package com.cinema.booker.repository;


import com.cinema.booker.model.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

    //Page<Movie> findAll(Pageable pageable);
}
