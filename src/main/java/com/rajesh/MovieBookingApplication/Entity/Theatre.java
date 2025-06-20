package com.rajesh.MovieBookingApplication.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String theatreName;
    private String theatreLocation;
    private int theatreCapacity;
    private String theatreScreenType;
    @OneToMany(mappedBy = "theatre", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Show> show;
}
