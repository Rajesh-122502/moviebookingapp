package com.rajesh.MovieBookingApplication.Service;

import com.rajesh.MovieBookingApplication.DTO.ShowDTO;
import com.rajesh.MovieBookingApplication.Entity.Booking;
import com.rajesh.MovieBookingApplication.Entity.Movie;
import com.rajesh.MovieBookingApplication.Entity.Show;
import com.rajesh.MovieBookingApplication.Entity.Theatre;
import com.rajesh.MovieBookingApplication.ExceptionHandlers.NoMovieFoundException;
import com.rajesh.MovieBookingApplication.Repository.MovieRepository;
import com.rajesh.MovieBookingApplication.Repository.ShowRepository;
import com.rajesh.MovieBookingApplication.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheatreRepository theatreRepository;
    public Show createShow(ShowDTO showDTO){
        Movie movie= movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(()->new NoMovieFoundException("No movie found for id "+showDTO.getMovieId()));
        Theatre theatre= theatreRepository.findById(showDTO.getTheatreId())
                .orElseThrow(()->new RuntimeException("No theatre found for id "+showDTO.getTheatreId()));
        Show show= new Show();
        show.setShowTime(showDTO.getShowTime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheatre(theatre);
        return showRepository.save(show);
    }
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }
    public List<Show> getShowsByMovie(long id){
        Optional<List<Show>> listofmovies= showRepository.findByMovieId(id);
        if(listofmovies.isPresent()){
            return listofmovies.get();
        }
        else throw new RuntimeException("no shows available for the movie "+id);
    }
    public List<Show> getShowsByTheatre(long id){
        Optional<List<Show>> listofmovies= showRepository.findByTheatreId(id);
        if(listofmovies.isPresent()){
            return listofmovies.get();
        }
        else throw new RuntimeException("No show available for the theatre "+id);
    }
    public Show updateShow(long id, ShowDTO showDTO) {
        Show show= showRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No show found for the id "+id));
        Movie movie= movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(()->new RuntimeException("No movie found for the movie "+showDTO.getMovieId()));
        Theatre theatre= theatreRepository.findById(showDTO.getTheatreId())
                .orElseThrow(()->new RuntimeException("No theatre found for the id "+showDTO.getTheatreId()));
        show.setShowTime(showDTO.getShowTime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheatre(theatre);
        return showRepository.save(show);
    }
    public void deleteShow(long id){
        if (!showRepository.existsById(id)){
            throw new RuntimeException("No show available to delete for the id "+id);
        }
        List<Booking> bookings= showRepository.findById(id).get().getBookings();
        if (!bookings.isEmpty()){
            throw new RuntimeException("can't delete this show because of existing shows");
        }
        showRepository.deleteById(id);
    }
}
