package com.cinema.booker.service;

import com.cinema.booker.model.Movie;
import com.cinema.booker.model.Showtime;
import com.cinema.booker.model.Theatre;
import com.cinema.booker.repository.MovieRepository;
import com.cinema.booker.repository.ShowtimeRepository;
import com.cinema.booker.repository.TheatreRepository;
import com.cinema.booker.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataImportService {

    private final MovieRepository movieRepository;

    private final TheatreRepository theatreRepository;

    private final ShowtimeRepository showtimeRepository;

    @Transactional
    public void importTimeSlotData() {
        Page<Movie> movies = movieRepository.findAll(Pageable.unpaged());
        Page<Theatre> theatres = theatreRepository.findAll(Pageable.unpaged());
        for (Movie movie : movies) {
            for (Theatre theatre : theatres) {
                Showtime st1 = Showtime.builder()
                        .movieId(movie.getId())
                        .theatreId(theatre.getId())
                        .showTime(DateTimeUtils.createFromToday(0, 12, 15))
                        .build();
                Showtime st2 = Showtime.builder()
                        .movieId(movie.getId())
                        .theatreId(theatre.getId())
                        .showTime(DateTimeUtils.createFromToday(0, 14, 30))
                        .build();
                Showtime st3 = Showtime.builder()
                        .movieId(movie.getId())
                        .theatreId(theatre.getId())
                        .showTime(DateTimeUtils.createFromToday(0, 16, 45))
                        .build();

                showtimeRepository.saveAll(List.of(st1, st2, st3));
            }
        }
    }
}
