package com.cinema.booker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Ticket {

    @Id
    private long id;

    private long showtimeId;
    private long authUserId;
    private long seatRows;
    private long seatColumns;
}
