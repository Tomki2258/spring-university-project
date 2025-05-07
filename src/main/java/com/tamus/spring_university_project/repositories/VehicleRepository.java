package com.tamus.spring_university_project.repositories;


import com.tamus.spring_university_project.models.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {
    List<Vehicle> findAll();
    Optional<Vehicle> findById(String id);
    Vehicle addToDatabase(Vehicle vehicle);
    void deleteById(String id);
    void save();
}