package com.rajesh.MovieBookingApplication.ExceptionHandlers;

public class MovieAlreadyExistException extends RuntimeException{
    public MovieAlreadyExistException(String message){
        super(message);
    }
}
