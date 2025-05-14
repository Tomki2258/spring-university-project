package com.tamus.spring_university_project.repositories;


import com.tamus.spring_university_project.models.Rental;

import java.util.List;
import java.util.Optional;

public interface IRentalRepository {
    void fn();
    List<Rental> getRentals();
    void add(Rental rental);
    boolean returnVehicle(String userUD, String vehicleID);
    boolean isVehicleRented(String vehicleId);
    Optional<Rental> findActiveRentalByVehicleId(String vehicleId);
    Rental rent(String vehicleId,String userId);
}
