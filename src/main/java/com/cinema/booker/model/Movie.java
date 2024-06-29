package com.cinema.booker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Data
public class Movie {

    /*@Id
    @SequenceGenerator(name = "movie_seq_generator", sequenceName="movie_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="movie_seq_generator")*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private long id;

    @JsonProperty
    private String title;

    @JsonProperty
    private String description;

    @JsonProperty
    private String genre;

    @JsonProperty
    private int duration;
}
