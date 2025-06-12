package com.rajesh.MovieBookingApplication.Repository;

import com.rajesh.MovieBookingApplication.Entity.Booking;
import com.rajesh.MovieBookingApplication.Entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(long id);
    List<Booking> findByShowId(long id);
    List<Booking> findByBookingStatus(BookingStatus bookingStatus);
}
