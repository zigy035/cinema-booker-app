package com.cinema.booker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Theatre {

    @Id
    private long id;

    private String name;
    private int seatRows;
    private int seatColumns;
}
