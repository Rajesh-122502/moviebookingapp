package com.rajesh.MovieBookingApplication.Controller;

import com.rajesh.MovieBookingApplication.DTO.MovieDTO;
import com.rajesh.MovieBookingApplication.Entity.Movie;
import com.rajesh.MovieBookingApplication.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @PostMapping("/addmovie")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDTO movieDTO){
        return ResponseEntity.ok(movieService.addMovie(movieDTO));
    }
    @GetMapping("/getallmovies")
    public ResponseEntity<List<Movie>> getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }
    @GetMapping("/getmoviesbygenre")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@RequestParam String genre){
        return ResponseEntity.ok(movieService.getMoviesByGenre(genre));
    }
    @GetMapping("/getmoviesbylanguage")
    public ResponseEntity<List<Movie>> getMoviesByLanguage(@RequestParam String language){
        return ResponseEntity.ok(movieService.getMoviesByLanguage(language));
    }
    @GetMapping("/getmoviebyname")
    public ResponseEntity<Movie> getMovieByName(@RequestParam String name){
        return ResponseEntity.ok(movieService.getMovieByName(name));
    }
    @PutMapping("/updatemovie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO){
        return ResponseEntity.ok(movieService.updateMovie(id, movieDTO));
    }
    @DeleteMapping("/deletemovie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }
}
