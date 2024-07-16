package com.cinema.booker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

import static com.cinema.booker.util.DateTimeUtils.ISO_DATE_TIME_FORMAT;


@Data
@Builder
@Table("showtime")
public class Showtime {

    @Id
    private long id;

    private long movieId;

    private long theatreId;

    @JsonFormat(pattern = ISO_DATE_TIME_FORMAT)
    private LocalDateTime showTime;
}
