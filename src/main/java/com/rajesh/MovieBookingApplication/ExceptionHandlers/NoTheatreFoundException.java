package com.rajesh.MovieBookingApplication.ExceptionHandlers;

public class NoTheatreFoundException extends RuntimeException{
    public NoTheatreFoundException(String message){
        super(message);
    }
}