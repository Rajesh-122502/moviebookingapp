package com.rajesh.MovieBookingApplication.ExceptionHandlers;

public class NoMovieFoundException extends RuntimeException{
    public NoMovieFoundException(String message){
        super(message);
    }
}
