package com.tamus.spring_university_project.controllers;

import com.tamus.spring_university_project.User;
import com.tamus.spring_university_project.dto.LoginRequest;
import com.tamus.spring_university_project.dto.LoginResponse;
import com.tamus.spring_university_project.models.Rental;
import com.tamus.spring_university_project.security.JwtUtil;
import com.tamus.spring_university_project.services.RentalService;
import com.tamus.spring_university_project.services.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Console;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Login:"+loginRequest.getLogin() +" Password:"+ loginRequest.getPassword());
        Authentication auth;
        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getLogin(),
                            loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            System.out.println("Błedne dane logowania!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        LoginResponse responseBody = new LoginResponse(token);
        return ResponseEntity.ok(responseBody);
    }
    @PostMapping("/rent")
    public ResponseEntity<Rental> rentVehicle(
            @RequestBody RentalRequest rentalRequest,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UserService userService = new UserService();
        RentalService rentalService = new RentalService();
        //String login = userDetails.getUsername();
        String login = "admin";
        User user = userService.getUserByNick(login);
        Rental rental = rentalService.rent(rentalRequest.getVehicleId(), user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(rental);
    }
    @Getter
    public static class RentalRequest {
        private String vehicleId;
    }
}
