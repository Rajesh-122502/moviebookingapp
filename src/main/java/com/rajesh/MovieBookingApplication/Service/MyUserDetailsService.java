package com.rajesh.MovieBookingApplication.Service;

import com.rajesh.MovieBookingApplication.Entity.User;
import com.rajesh.MovieBookingApplication.Entity.UserPrincipal;
import com.rajesh.MovieBookingApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= repo.findByUsername(username);
        if(user==null){
            System.out.println("wrong credentials");
            throw new UsernameNotFoundException("401 user not found");
        }
        return new UserPrincipal(user);
    }
}
