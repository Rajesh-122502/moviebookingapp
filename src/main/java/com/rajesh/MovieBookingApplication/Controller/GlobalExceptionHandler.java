package com.rajesh.MovieBookingApplication.Controller;

import com.rajesh.MovieBookingApplication.ExceptionHandlers.MovieAlreadyExistException;
import com.rajesh.MovieBookingApplication.ExceptionHandlers.NoMovieFoundException;
import com.rajesh.MovieBookingApplication.ExceptionHandlers.NoTheatreFoundException;
import com.rajesh.MovieBookingApplication.ExceptionHandlers.TheatreAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MovieAlreadyExistException.class)
    public ResponseEntity<String> handleMovieAlreadyExistException(MovieAlreadyExistException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    @ExceptionHandler(NoMovieFoundException.class)
    public ResponseEntity<String> handleNoMovieFoundException(NoMovieFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(TheatreAlreadyExistException.class)
    public ResponseEntity<String> handleTheatreAlreadyExistException(TheatreAlreadyExistException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    @ExceptionHandler(NoTheatreFoundException.class)
    public ResponseEntity<String> handleNoTheatreFoundException(NoTheatreFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
