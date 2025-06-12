package com.rajesh.MovieBookingApplication.Controller;

import com.rajesh.MovieBookingApplication.DTO.BookingDTO;
import com.rajesh.MovieBookingApplication.Entity.Booking;
import com.rajesh.MovieBookingApplication.Entity.BookingStatus;
import com.rajesh.MovieBookingApplication.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @PostMapping("/createbooking")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO){
        return ResponseEntity.ok(bookingService.createBooking(bookingDTO));
    }

    @GetMapping("/getuserbookings/{id}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable long id){//here id is user id
        return ResponseEntity.ok(bookingService.getUserBookings(id));
    }
    @GetMapping("/getshowbookings/{id}")
    public ResponseEntity<List<Booking>> getShowBookings(@PathVariable long id){//here id is show id
        return ResponseEntity.ok(bookingService.getShowBookings(id));
    }
    @PutMapping("/{id}/confirm")
    public ResponseEntity<Booking> confirmBooking(@PathVariable long id){//here id is booking id
        return ResponseEntity.ok(bookingService.confirmBooking(id));
    }
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable long id){//here id is booking id
        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }
    @GetMapping("/getbookingsbystatus/{bookingStatus}")
    public ResponseEntity<List<Booking>> getBookingsByStatus(@PathVariable BookingStatus bookingStatus){
        return ResponseEntity.ok(bookingService.getBookingsByStatus(bookingStatus));
    }
}
