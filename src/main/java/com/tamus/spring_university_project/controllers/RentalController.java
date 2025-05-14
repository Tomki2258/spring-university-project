package com.tamus.spring_university_project.controllers;

import com.tamus.spring_university_project.db.JdbcConnectionManager;
import com.tamus.spring_university_project.models.Rental;
import com.tamus.spring_university_project.repositories.Jdbc.RentalJdbcRepository;
import com.tamus.spring_university_project.services.IRentalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class RentalController implements IRentalService {
    RentalJdbcRepository rentalJdbcRepository = new RentalJdbcRepository();

    @GetMapping("/rentals/isVehicleRented")
    @Override
    public boolean isVehicleRented(@RequestParam String vehicleId) {
        return rentalJdbcRepository.isVehicleRented(vehicleId);
    }

    @GetMapping("/rentals/findActiveRentalByVehicleId")
    @Override
    public Optional<Rental> findActiveRentalByVehicleId(@RequestParam String vehicleId) {
        return rentalJdbcRepository.findActiveRentalByVehicleId(vehicleId);
    }

    @GetMapping("/rentals/rentRental")
    @Override
    public Rental rent(@RequestParam String vehicleId, @RequestParam String userId) {
        return rentalJdbcRepository.rent(vehicleId,userId);
    }

    @GetMapping("/rentals/returnRental")
    @Override
    public boolean returnRental(@RequestParam String vehicleId, @RequestParam String userId) {
        return rentalJdbcRepository.returnVehicle(userId, vehicleId);
    }

    @GetMapping("/rentals/findAll")
    @Override
    public List<Rental> findAll() {
        return rentalJdbcRepository.getRentals();
    }
}
