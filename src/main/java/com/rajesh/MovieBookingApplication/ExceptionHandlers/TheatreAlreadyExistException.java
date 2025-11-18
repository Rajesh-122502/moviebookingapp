package com.rajesh.MovieBookingApplication.ExceptionHandlers;

public class TheatreAlreadyExistException extends RuntimeException{
    public TheatreAlreadyExistException(String message){
        super(message);
    }
}
