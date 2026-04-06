package com.cinema.booker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "theatre")
public class Theatre {

    @Id
    private long id;

    private String name;
    private int seatRows;
    private int seatColumns;
}
