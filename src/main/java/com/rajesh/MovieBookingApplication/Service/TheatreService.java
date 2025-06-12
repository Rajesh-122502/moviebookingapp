package com.rajesh.MovieBookingApplication.Service;

import com.rajesh.MovieBookingApplication.DTO.TheatreDTO;
import com.rajesh.MovieBookingApplication.Entity.Theatre;
import com.rajesh.MovieBookingApplication.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;
    public Theatre addTheatre(TheatreDTO theatreDTO){
        Theatre theatre= new Theatre();
        theatre.setTheatreName(theatreDTO.getTheatreName());
        theatre.setTheatreCapacity(theatreDTO.getTheatreCapacity());
        theatre.setTheatreLocation(theatreDTO.getTheatreLocation());
        theatre.setTheatreScreenType(theatreDTO.getTheatreScreenType());
        return theatreRepository.save(theatre);
    }
    public List<Theatre> getTheatreByLocation(String location){
        Optional<List<Theatre>> listoftheatres= theatreRepository.findByTheatreLocation(location);
        if(listoftheatres.isPresent()){
            return listoftheatres.get();
        }
        else throw new RuntimeException("no theatre found for the location "+location);
    }
    public Theatre updateTheatre(long id, TheatreDTO theatreDTO){
        Theatre theatre= theatreRepository.findById(id).orElseThrow(()->new RuntimeException("no theatre found for this id "+id));
        theatre.setTheatreName(theatreDTO.getTheatreName());
        theatre.setTheatreCapacity(theatreDTO.getTheatreCapacity());
        theatre.setTheatreLocation(theatreDTO.getTheatreLocation());
        theatre.setTheatreScreenType(theatreDTO.getTheatreScreenType());
        return theatreRepository.save(theatre);
    }
    public void deleteTheatre(long id){
        theatreRepository.deleteById(id);
    }
}
