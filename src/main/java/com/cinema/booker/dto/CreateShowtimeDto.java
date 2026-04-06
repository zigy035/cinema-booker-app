package com.cinema.booker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

import static com.cinema.booker.util.DateTimeUtils.ISO_DATE_TIME_FORMAT;

@Data
@Schema(description = "Showtime creation request")
public class CreateShowtimeDto {

    @Schema(description = "Movie ID", example = "123", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Movie ID is required")
    private Long movieId;

    @Schema(description = "Theatre ID", example = "123", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Theatre ID is required")
    private Long theatreId;

    @NotNull(message = "Show time is required")
    @Future(message = "Show time must be in the future")
    @JsonFormat(pattern = ISO_DATE_TIME_FORMAT)
    private LocalDateTime showTime;
}
