package com.cinema.booker.service;

import com.cinema.booker.dto.CreateShowtimeDto;
import com.cinema.booker.exception.ShowtimeOverlapException;
import com.cinema.booker.model.Movie;
import com.cinema.booker.model.Showtime;
import com.cinema.booker.repository.MovieRepository;
import com.cinema.booker.repository.ShowtimeRepository;
import com.cinema.booker.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowtimeService {

    private final ShowtimeRepository showtimeRepository;
    private final MovieRepository movieRepository;

    public List<Showtime> getShowtimesByMovieAndDate(long movieId, LocalDate localDate) {
        return showtimeRepository.getShowtimesByMovieAndDate(movieId, localDate);
    }

    public TreeMap<String, SortedSet<String>> getShowtimeSlots(long movieId) {
        List<Showtime> showtimes = showtimeRepository.getShowtimesByMovieId(movieId);
        Map<String, Set<String>> hashMap = showtimes.stream()
                .map(Showtime::getShowTime)
                .collect(Collectors.groupingBy(DateTimeUtils::getDateAsString,
                        Collectors.mapping(DateTimeUtils::getTimeAsString, Collectors.toSet())));

        TreeMap<String, SortedSet<String>> treeMap = new TreeMap<>();
        hashMap.forEach((k, v) -> treeMap.put(k, new TreeSet<>(v)));
        return treeMap;
    }

    @Transactional
    public List<Showtime> createShowtimes(List<CreateShowtimeDto> dtos) {
        List<Showtime> toSave = new ArrayList<>();

        for (CreateShowtimeDto dto : dtos) {
            Movie movie = movieRepository.findById(dto.getMovieId())
                    .orElseThrow(() -> new NoSuchElementException("Movie not found: " + dto.getMovieId()));

            LocalDateTime newStart = dto.getShowTime();
            LocalDateTime newEnd = newStart.plusMinutes(movie.getDuration());

            // Check against existing showtimes in the same theatre
            for (Showtime existing : showtimeRepository.findByTheatreId(dto.getTheatreId())) {
                Movie existingMovie = movieRepository.findById(existing.getMovieId())
                        .orElseThrow(() -> new NoSuchElementException("Movie not found: " + existing.getMovieId()));
                LocalDateTime existingStart = existing.getShowTime();
                LocalDateTime existingEnd = existingStart.plusMinutes(existingMovie.getDuration());

                if (newStart.isBefore(existingEnd) && existingStart.isBefore(newEnd)) {
                    throw new ShowtimeOverlapException(dto.getTheatreId(), newStart.toString());
                }
            }

            // Check against other showtimes in the same batch (same theatre)
            for (Showtime pending : toSave) {
                if (pending.getTheatreId() == dto.getTheatreId()) {
                    Movie pendingMovie = movieRepository.findById(pending.getMovieId())
                            .orElseThrow(() -> new NoSuchElementException("Movie not found: " + pending.getMovieId()));
                    LocalDateTime pendingStart = pending.getShowTime();
                    LocalDateTime pendingEnd = pendingStart.plusMinutes(pendingMovie.getDuration());

                    if (newStart.isBefore(pendingEnd) && pendingStart.isBefore(newEnd)) {
                        throw new ShowtimeOverlapException(dto.getTheatreId(), newStart.toString());
                    }
                }
            }

            toSave.add(Showtime.builder()
                    .movieId(dto.getMovieId())
                    .theatreId(dto.getTheatreId())
                    .showTime(newStart)
                    .build());
        }

        return showtimeRepository.saveAll(toSave);
    }
}
