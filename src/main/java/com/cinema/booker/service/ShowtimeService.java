package com.cinema.booker.service;

import com.cinema.booker.model.Showtime;
import com.cinema.booker.repository.ShowtimeRepository;
import com.cinema.booker.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowtimeService {

    private final ShowtimeRepository showtimeRepository;

    public List<Showtime> getShowtimesByMovieAndDate(long movieId, LocalDate localDate) {
        return showtimeRepository.getShowtimesByMovieAndDate(movieId, localDate);
    }

    /*public List<Showtime> getShowtimesByMovieId(long movieId) {
        return showtimeRepository.getShowtimesByMovieId(movieId);
    }*/

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
}
