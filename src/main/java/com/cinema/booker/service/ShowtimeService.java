package com.cinema.booker.service;

import com.cinema.booker.dto.CreateShowtimeDto;
import com.cinema.booker.dto.CreateShowtimeListDto;
import com.cinema.booker.exception.RecordNotFoundException;
import com.cinema.booker.exception.ShowtimeOverlapException;
import com.cinema.booker.model.Movie;
import com.cinema.booker.model.Showtime;
import com.cinema.booker.repository.MovieRepository;
import com.cinema.booker.repository.ShowtimeRepository;
import com.cinema.booker.repository.TheatreRepository;
import com.cinema.booker.util.DateTimeUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShowtimeService {

    private final ShowtimeRepository showtimeRepository;
    private final MovieRepository movieRepository;
    private final TheatreRepository theatreRepository;

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
    public List<Showtime> createShowtimes(CreateShowtimeListDto dtos) {
        List<Showtime> toSaveShowtimes = new ArrayList<>();

        for (CreateShowtimeDto dto : dtos.getShowtimes()) {
            Movie movie = movieRepository.findById(dto.getMovieId())
                    .orElseThrow(() -> new RecordNotFoundException(dto.getMovieId(), "Movie"));
            theatreRepository.findById(dto.getTheatreId())
                    .orElseThrow(() -> new RecordNotFoundException(dto.getTheatreId(), "Theatre"));

            LocalDateTime newStart = dto.getShowTime().withSecond(0).withNano(0);
            LocalDateTime newEnd = newStart.plusMinutes(movie.getDuration());

            // Check against existing showtimes in the same theatre
            for (Showtime existingShowtime : showtimeRepository.findByTheatreId(dto.getTheatreId())) {
                Movie existingMovie = movieRepository.findById(existingShowtime.getMovieId())
                        .orElseThrow(() -> new RecordNotFoundException(existingShowtime.getMovieId(), "Movie"));
                LocalDateTime existingStart = existingShowtime.getShowTime();
                LocalDateTime existingEnd = existingStart.plusMinutes(existingMovie.getDuration());

                if (newStart.isBefore(existingEnd) && existingStart.isBefore(newEnd)) {
                    throw new ShowtimeOverlapException(dto.getTheatreId(), newStart.toString());
                }
            }

            // Check against other showtimes in the same batch (same theatre)
            for (Showtime pendingShowtime : toSaveShowtimes) {
                if (pendingShowtime.getTheatreId() == dto.getTheatreId()) {
                    Movie pendingMovie = movieRepository.findById(pendingShowtime.getMovieId())
                            .orElseThrow(() -> new RecordNotFoundException(pendingShowtime.getMovieId(), "Movie"));
                    LocalDateTime pendingStart = pendingShowtime.getShowTime();
                    LocalDateTime pendingEnd = pendingStart.plusMinutes(pendingMovie.getDuration());

                    if (newStart.isBefore(pendingEnd) && pendingStart.isBefore(newEnd)) {
                        throw new ShowtimeOverlapException(dto.getTheatreId(), newStart.toString());
                    }
                }
            }

            toSaveShowtimes.add(Showtime.builder()
                    .movieId(dto.getMovieId())
                    .theatreId(dto.getTheatreId())
                    .showTime(newStart)
                    .build());
        }

        return showtimeRepository.saveAll(toSaveShowtimes);
    }
}
