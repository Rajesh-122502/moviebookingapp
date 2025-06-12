package com.rajesh.MovieBookingApplication.DTO;

import lombok.Data;

@Data
public class TheatreDTO {
    private String theatreName;
    private String theatreLocation;
    private int theatreCapacity;
    private String theatreScreenType;
}
