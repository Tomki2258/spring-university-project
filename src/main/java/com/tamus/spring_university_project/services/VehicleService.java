package com.tamus.spring_university_project.services;

import com.tamus.spring_university_project.models.Vehicle;
import com.tamus.spring_university_project.repositories.Jdbc.VehicleJdbcRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public class VehicleService {
    private final VehicleJdbcRepository vehicleJdbcRepository = new VehicleJdbcRepository();

    public String findAll(){
        return vehicleJdbcRepository.findAll().toString();
    }
    public String findActive(){
        return vehicleJdbcRepository.findAllActive().toString();
    }
    public String findById(String id){
        Optional<Vehicle> result = vehicleJdbcRepository.findById(id);
        if(result.isPresent()) {
            return result.toString();
        }
        return "Vehicle not found";
    }
    public String findAvailableVehicles(){
        return vehicleJdbcRepository.findAvailableVehicles().toString();
    }
    public String findRentedVehicles(){
        return vehicleJdbcRepository.findRentedVehicles().toString();
    }
    public boolean isAvailable(@RequestParam String id){
        return vehicleJdbcRepository.isAvailable(id);
    }
}
