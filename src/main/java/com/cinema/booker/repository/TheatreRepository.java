package com.cinema.booker.repository;

import com.cinema.booker.model.Theatre;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TheatreRepository extends PagingAndSortingRepository<Theatre, Long> {
}
