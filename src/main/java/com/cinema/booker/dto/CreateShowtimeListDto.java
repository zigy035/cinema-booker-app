package com.cinema.booker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Data;

@Schema(description = "Request body for creating multiple showtimes")
@Data
public class CreateShowtimeListDto {

    @Schema(description = "List of users to create", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "Showtime list must not be empty")
    private List<CreateShowtimeDto> showtimes;
}
