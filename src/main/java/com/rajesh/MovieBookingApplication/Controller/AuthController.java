package com.rajesh.MovieBookingApplication.Controller;

import com.rajesh.MovieBookingApplication.DTO.LoginRequestDTO;
import com.rajesh.MovieBookingApplication.DTO.LoginResponseDTO;
import com.rajesh.MovieBookingApplication.DTO.RegisterRequestDTO;
import com.rajesh.MovieBookingApplication.Entity.User;
import com.rajesh.MovieBookingApplication.Service.JwtService;
import com.rajesh.MovieBookingApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody RegisterRequestDTO user){
        return ResponseEntity.ok(userService.saveUser(user));
    }
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO){
        User user= userService.findUserByUsername(loginRequestDTO.getUsername());
        if (user == null){
            throw new RuntimeException("user not found");
        }
        Authentication authentication= authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(),loginRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            String token= jwtService.generateToken(loginRequestDTO.getUsername());
            return LoginResponseDTO.builder()
                    .jwtToken(token)
                    .username(user.getUsername())
                    .build();
        }
        else {
            throw new RuntimeException("login failed");
        }
    }
}
