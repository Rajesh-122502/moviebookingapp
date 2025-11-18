package com.rajesh.MovieBookingApplication.Repository;

import com.rajesh.MovieBookingApplication.Entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    Optional<List<Theatre>> findByTheatreLocation(String location);
    Optional<Theatre> findByTheatreName(String name);
}
