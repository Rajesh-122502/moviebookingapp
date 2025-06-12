package com.rajesh.MovieBookingApplication.Service;

import com.rajesh.MovieBookingApplication.DTO.BookingDTO;
import com.rajesh.MovieBookingApplication.Entity.Booking;
import com.rajesh.MovieBookingApplication.Entity.BookingStatus;
import com.rajesh.MovieBookingApplication.Entity.Show;
import com.rajesh.MovieBookingApplication.Entity.User;
import com.rajesh.MovieBookingApplication.Repository.BookingRepository;
import com.rajesh.MovieBookingApplication.Repository.ShowRepository;
import com.rajesh.MovieBookingApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private UserRepository userRepository;

    //CREATE BOOKING API STARTS
    public Booking createBooking(BookingDTO bookingDTO){
        Show show= showRepository.findById(bookingDTO.getShowId())
                .orElseThrow(()->new RuntimeException("Show not found"));
        if (!isSeatsAvailable(show.getId(), bookingDTO.getNumberOfSeats())){
            throw new RuntimeException("Not enough seats are available");
        }
        if (bookingDTO.getSeatNumbers().size() != bookingDTO.getNumberOfSeats()){
            throw new RuntimeException("select the equal seats as entered in number of seats");
        }
        validateDuplicateSeats(show.getId(), bookingDTO.getSeatNumbers());
        User user= userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(()->new RuntimeException("user not found"));
        Booking booking= new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
        booking.setSeatNumbers(bookingDTO.getSeatNumbers());
        booking.setBookingPrice(calculateTotalPrice(show.getPrice(), bookingDTO.getNumberOfSeats()));
        booking.setBookingTime(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.PENDING);
        return bookingRepository.save(booking);
    }
    public boolean isSeatsAvailable(long showId, int numberOfSeats){
        Show show= showRepository.findById(showId)
                .orElseThrow(()->new RuntimeException("Show not found"));
        int bookedSeats= show.getBookings().stream()
                .filter((booking)->booking.getBookingStatus()!= BookingStatus.CANCELLED)
                .mapToInt(Booking::getNumberOfSeats)
                .sum();
        return (show.getTheatre().getTheatreCapacity()-bookedSeats)>=numberOfSeats;
    }
    public void validateDuplicateSeats(long showId, List<String> seatNumbers){
        Show show= showRepository.findById(showId)
                .orElseThrow(()->new RuntimeException("show not found"));
        Set<String> occupiedSeats= show.getBookings().stream()
                .filter(booking->booking.getBookingStatus()!=BookingStatus.CANCELLED)
                .flatMap(booking->booking.getSeatNumbers().stream())
                .collect(Collectors.toSet());
        List<String> duplicateSeats= seatNumbers.stream()
                .filter(occupiedSeats::contains)
                .collect(Collectors.toList());
        if (!duplicateSeats.isEmpty()){
            throw new RuntimeException("selected seats are already booked");
        }
    }
    public double calculateTotalPrice(double price, int numberOfSeats){
        return price*numberOfSeats;
    }
    //CREATE BOOKING API ENDS

    public List<Booking> getUserBookings(long userid){
        return bookingRepository.findByUserId(userid);
    }
    public List<Booking> getShowBookings(long showid){
        return bookingRepository.findByShowId(showid);
    }
    public Booking confirmBooking(long bookingid){
        Booking booking= bookingRepository.findById(bookingid)
                .orElseThrow(()->new RuntimeException("booking not found"));
        if (booking.getBookingStatus() != BookingStatus.PENDING){
            throw new RuntimeException("booking is not in pending");
        }
        //ASK FOR PAYMENT CONFIRMATION(PAYMENT API PROCESS)
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        return bookingRepository.save(booking);
    }
    public Booking cancelBooking(long bookingid){
        Booking booking= bookingRepository.findById(bookingid)
                .orElseThrow(()->new RuntimeException("no booking found"));
        validateCancellation(booking);
        booking.setBookingStatus(BookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }
    public void validateCancellation(Booking booking) {
        LocalDateTime showTime = booking.getShow().getShowTime();
        LocalDateTime deadlineTime = showTime.minusHours(2);
        if (LocalDateTime.now().isAfter(deadlineTime)) {
            throw new RuntimeException("can not cancel the booking");
        }
        if (booking.getBookingStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Booking has already been cancelled");
        }
    }
    public List<Booking> getBookingsByStatus(BookingStatus bookingStatus){
        return bookingRepository.findByBookingStatus(bookingStatus);
    }
}
