package com.cinema.booker.controller;

import com.cinema.booker.model.Broadcast;
import com.cinema.booker.service.BroadcastService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeMap;

@RestController
@RequestMapping("/api/broadcasts")
@RequiredArgsConstructor
public class BroadcastController {

    private final BroadcastService broadcastService;

    @GetMapping("/movie")
    public List<Broadcast> getBroadcastsByMovieAndDate(
            @RequestParam("movieId") long movieId,
            @RequestParam("localDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        return broadcastService.getBroadcastsByMovieAndDate(movieId, localDate);
    }

    @GetMapping("/movie/{movieId}")
    public TreeMap<String, SortedSet<String>> getBroadcastTimeSlots(@PathVariable("movieId") long movieId) {
        return broadcastService.getBroadcastTimeSlots(movieId);
    }
}
