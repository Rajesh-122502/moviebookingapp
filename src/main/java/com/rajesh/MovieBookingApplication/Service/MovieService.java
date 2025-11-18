package com.rajesh.MovieBookingApplication.Service;

import com.rajesh.MovieBookingApplication.DTO.MovieDTO;
import com.rajesh.MovieBookingApplication.Entity.Movie;
import com.rajesh.MovieBookingApplication.ExceptionHandlers.MovieAlreadyExistException;
import com.rajesh.MovieBookingApplication.ExceptionHandlers.NoMovieFoundException;
import com.rajesh.MovieBookingApplication.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public Movie addMovie(MovieDTO movieDTO) {
        Optional<Movie> movie1= movieRepository.findByName(movieDTO.getName());
        if(movie1.isPresent()){
            throw new MovieAlreadyExistException("movie with the name "+movieDTO.getName()+ " already present.");
        }
        Movie movie= new Movie();
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setGenre(movieDTO.getGenre());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDuration(movieDTO.getDuration());
        movie.setLanguage(movieDTO.getLanguage());
        return movieRepository.save(movie);
    }
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
    public List<Movie> getMoviesByGenre(String genre){
        Optional<List<Movie>> listofmoives= movieRepository.findByGenre(genre);
        if(!listofmoives.get().isEmpty()){
            return listofmoives.get();
        }
        else{
            throw new NoMovieFoundException("no movie found for genre "+genre);
        }
    }
    public List<Movie> getMoviesByLanguage(String language){
        Optional<List<Movie>> listofmoives= movieRepository.findByLanguage(language);
        if(listofmoives.isPresent()){
            return listofmoives.get();
        }
        else{
            throw new NoMovieFoundException("no movie found for language "+language);
        }
    }
    public Movie getMovieByName(String name){
        Optional<Movie> moviebyname= movieRepository.findByName(name);
        if(moviebyname.isPresent()){
            return moviebyname.get();
        }
        else{
            throw new NoMovieFoundException("no movie found for name "+name);
        }
    }

    public Movie updateMovie(Long id, MovieDTO movieDTO) {
        Movie movie= movieRepository.findById(id).orElseThrow(()->new NoMovieFoundException("no movie found for the id "+id));
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setGenre(movieDTO.getGenre());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDuration(movieDTO.getDuration());
        movie.setLanguage(movieDTO.getLanguage());
        return movieRepository.save(movie);
    }
    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }
}
