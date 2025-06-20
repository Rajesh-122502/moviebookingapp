package com.rajesh.MovieBookingApplication.Service;


import com.rajesh.MovieBookingApplication.DTO.RegisterRequestDTO;
import com.rajesh.MovieBookingApplication.Entity.User;
import com.rajesh.MovieBookingApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(4);
    @Autowired
    private UserRepository repo;
    public User saveUser(RegisterRequestDTO registerRequestDTO){
        if (repo.findByUsername(registerRequestDTO.getUsername())!=null){
            throw new RuntimeException("user already present");
        }
        User user= new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(encoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(Set.of("USER"));
        return repo.save(user);
    }

    public User findUserByUsername(String username) {
        return repo.findByUsername(username);
    }
}
