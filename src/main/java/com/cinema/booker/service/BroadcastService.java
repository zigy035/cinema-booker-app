package com.cinema.booker.service;

import com.cinema.booker.model.Broadcast;
import com.cinema.booker.repository.BroadcastRepository;
import com.cinema.booker.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BroadcastService {

    private final BroadcastRepository broadcastRepository;

    public List<Broadcast> getBroadcastsByMovieAndDate(long movieId, LocalDate localDate) {
        return broadcastRepository.getBroadcastsByMovieAndDate(movieId, localDate);
    }

    /*public List<Broadcast> getBroadcastsByMovieId(long movieId) {
        return broadcastRepository.getBroadcastsByMovieId(movieId);
    }*/

    public TreeMap<String, SortedSet<String>> getBroadcastTimeSlots(long movieId) {
        List<Broadcast> broadcasts = broadcastRepository.getBroadcastsByMovieId(movieId);
        Map<String, Set<String>> hashMap = broadcasts.stream()
                .map(Broadcast::getBroadcastTime)
                .collect(Collectors.groupingBy(DateTimeUtils::getDateAsString,
                        Collectors.mapping(DateTimeUtils::getTimeAsString, Collectors.toSet())));

        TreeMap<String, SortedSet<String>> treeMap = new TreeMap<>();
        hashMap.forEach((k, v) -> treeMap.put(k, new TreeSet<>(v)));
        return treeMap;
    }
}
