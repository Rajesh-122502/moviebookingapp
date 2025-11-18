package com.rajesh.MovieBookingApplication.Controller;

import com.rajesh.MovieBookingApplication.DTO.ShowDTO;
import com.rajesh.MovieBookingApplication.Entity.Show;
import com.rajesh.MovieBookingApplication.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/show")
public class ShowController {
    @Autowired
    private ShowService showService;
    @PostMapping("/createshow")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Show> createShow(@RequestBody ShowDTO showDTO){
        return ResponseEntity.ok(showService.createShow(showDTO));
    }
    @GetMapping("/getallshows")
    public ResponseEntity<List<Show>> getAllShows(){
        return ResponseEntity.ok(showService.getAllShows());
    }
    @GetMapping("/getshowsbymovie/{id}")
    public ResponseEntity<List<Show>> getShowsByMovie(@PathVariable long id){
        return ResponseEntity.ok(showService.getShowsByMovie(id));
    }
    @GetMapping("/getshowsbytheatre/{id}")
    public ResponseEntity<List<Show>> getShowsByTheatre(@PathVariable long id){
        return ResponseEntity.ok(showService.getShowsByTheatre(id));
    }
    @PutMapping("/updateshow/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Show> updateShow(@PathVariable long id, @RequestBody ShowDTO showDTO){
        return ResponseEntity.ok(showService.updateShow(id, showDTO));
    }
    @DeleteMapping("/deleteshow/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteShow(@PathVariable long id){
        showService.deleteShow(id);
        return ResponseEntity.ok().build();
    }
}
