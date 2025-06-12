package com.rajesh.MovieBookingApplication.Controller;

import com.rajesh.MovieBookingApplication.DTO.TheatreDTO;
import com.rajesh.MovieBookingApplication.Entity.Theatre;
import com.rajesh.MovieBookingApplication.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatre")
public class TheatreController {
    @Autowired
    private TheatreService theatreService;
    @PostMapping("/addtheatre")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theatre> addTheatre(@RequestBody TheatreDTO theatreDTO){
        return ResponseEntity.ok(theatreService.addTheatre(theatreDTO));
    }
    @GetMapping("/gettheatrebylocation")
    public ResponseEntity<List<Theatre>> getTheatreByLocation(@RequestParam String location){
        return ResponseEntity.ok(theatreService.getTheatreByLocation(location));
    }
    @PutMapping("/updatetheatre/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theatre> updateTheatre(@PathVariable long id, @RequestBody TheatreDTO theatreDTO){
        return ResponseEntity.ok(theatreService.updateTheatre(id, theatreDTO));
    }
    @DeleteMapping("/deletetheatre/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTheatre(@PathVariable long id){
        theatreService.deleteTheatre(id);
        return ResponseEntity.ok().build();
    }
}
