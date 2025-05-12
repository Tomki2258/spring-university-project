package com.tamus.spring_university_project.controllers;

import com.tamus.spring_university_project.models.Vehicle;
import com.tamus.spring_university_project.repositories.Jdbc.VehicleJdbcRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
    @ResponseBody
    String findById(@RequestParam String id){
        Optional<Vehicle> result = vehicleJdbcRepository.findById(id);
        if(result.isPresent()) {
            return result.toString();
        }
        return "Vehicle not found";
    }
    @GetMapping("/vehicles/findAvailableVehicles")
    String findAvailableVehicles(){
        return vehicleJdbcRepository.findAvailableVehicles().toString();
    }
    @GetMapping("/vehicles/findRentedVehicles")
    String findRentedVehicles(){
        return vehicleJdbcRepository.findRentedVehicles().toString();
    }
    @GetMapping("/vehicles/isAvailable")
    @ResponseBody
    boolean isAvailable(@RequestParam String id){
        return vehicleJdbcRepository.isAvailable(id);
    }
    @GetMapping("/vehicles/text")
    String test(){
        return "sigma2";
    }
}
