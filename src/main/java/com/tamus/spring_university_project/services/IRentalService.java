package com.tamus.spring_university_project.services;

import com.tamus.spring_university_project.models.Rental;

import java.util.List;
import java.util.Optional;

public interface IRentalService {
    boolean isVehicleRented(String vehicleId);

    Optional<Rental> findActiveRentalByVehicleId(String vehicleId);

    Rental rent(String vehicleId, String userId);

    boolean returnRental(String vehicleId, String userId);

    List<Rental> findAll();
}
