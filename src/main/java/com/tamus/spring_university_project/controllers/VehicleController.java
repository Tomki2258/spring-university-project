package com.tamus.spring_university_project.controllers;

import com.tamus.spring_university_project.repositories.Jdbc.VehicleJdbcRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleController {
    VehicleJdbcRepository vehicleJdbcRepository = new VehicleJdbcRepository();
    @GetMapping("/vehicles/findAll")
    String findAll(){
        return vehicleJdbcRepository.findAll().toString();
    }
    @GetMapping("/vehicles/findActive")
    String findActive(){
        return vehicleJdbcRepository.findAllActive().toString();
    }
    @GetMapping("/vehicles/findById")
    String findById(){
        return vehicleJdbcRepository.findById("8618dcbf-4517-4825-ac29-e78141eaa970").get().toString();
    }
    @GetMapping("/vehicles/findAvailableVehicles")
    String findAvailableVehicles(){
        return vehicleJdbcRepository.findAvailableVehicles().toString();
    }
    @GetMapping("/vehicles/findRentedVehicles")
    String findRentedVehicles(){
        return vehicleJdbcRepository.findRentedVehicles().toString();
    }
}
