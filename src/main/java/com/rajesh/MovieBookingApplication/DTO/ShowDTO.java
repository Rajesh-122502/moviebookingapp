package com.rajesh.MovieBookingApplication.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowDTO {
    private LocalDateTime showTime;
    private double price;
    private long movieId;
    private long theatreId;
}
