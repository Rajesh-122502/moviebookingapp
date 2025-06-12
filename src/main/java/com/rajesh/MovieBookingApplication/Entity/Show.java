package com.rajesh.MovieBookingApplication.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime showTime;
    private double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;
    @OneToMany(mappedBy = "show", fetch = FetchType.LAZY)
    private List<Booking> bookings;
}
