package com.rajesh.MovieBookingApplication.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String genre;
    private int duration;
    private LocalDate releaseDate;
    private String language;
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Show> show;
}
