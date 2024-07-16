package com.cinema.booker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

import static com.cinema.booker.util.DateTimeUtils.ISO_DATE_TIME_FORMAT;

@Data
public class CreateShowtimeDto {

    @NotNull(message = "Movie ID is required")
    private Long movieId;

    @NotNull(message = "Theatre ID is required")
    private Long theatreId;

    @NotNull(message = "Show time is required")
    @Future(message = "Show time must be in the future")
    @JsonFormat(pattern = ISO_DATE_TIME_FORMAT)
    private LocalDateTime showTime;
}
