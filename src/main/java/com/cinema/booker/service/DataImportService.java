package com.cinema.booker.service;

import com.cinema.booker.model.Broadcast;
import com.cinema.booker.model.Movie;
import com.cinema.booker.model.Theatre;
import com.cinema.booker.repository.BroadcastRepository;
import com.cinema.booker.repository.MovieRepository;
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

    private final BroadcastRepository broadcastRepository;

    @Transactional
    public void importTimeSlotData() {
        Page<Movie> movies = movieRepository.findAll(Pageable.unpaged());
        Page<Theatre> theatres = theatreRepository.findAll(Pageable.unpaged());
        for (Movie movie : movies) {
            for (Theatre theatre : theatres) {
                Broadcast bc1 = Broadcast.builder()
                        .movie(movie)
                        .theatre(theatre)
                        .broadcastTime(DateTimeUtils.createFromToday(0, 12, 15))
                        .build();
                Broadcast bc2 = Broadcast.builder()
                        .movie(movie)
                        .theatre(theatre)
                        .broadcastTime(DateTimeUtils.createFromToday(0, 14, 30))
                        .build();
                Broadcast bc3 = Broadcast.builder()
                        .movie(movie)
                        .theatre(theatre)
                        .broadcastTime(DateTimeUtils.createFromToday(0, 16, 45))
                        .build();

                broadcastRepository.saveAll(List.of(bc1, bc2, bc3));
            }
            LocalDateTime ldt = LocalDateTime.now().withHour(0);
        }
    }
}
