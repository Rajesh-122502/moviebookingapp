package com.rajesh.MovieBookingApplication.DTO;

import com.rajesh.MovieBookingApplication.Entity.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingDTO {
    private int numberOfSeats;
    private LocalDateTime bookingTime;
    private double bookingPrice;
    private BookingStatus bookingStatus;
    private List<String> seatNumbers;
    private long userId;
    private long showId;
}
