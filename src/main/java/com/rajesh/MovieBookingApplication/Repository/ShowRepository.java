package com.rajesh.MovieBookingApplication.Repository;

import com.rajesh.MovieBookingApplication.Entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    Optional<List<Show>> findByMovieId(long id);
    Optional<List<Show>> findByTheatreId(long id);
}
