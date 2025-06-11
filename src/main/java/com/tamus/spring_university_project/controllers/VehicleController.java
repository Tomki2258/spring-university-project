package com.tamus.spring_university_project.controllers;

import com.tamus.spring_university_project.models.Vehicle;
import com.tamus.spring_university_project.repositories.Jdbc.VehicleJdbcRepository;
import com.tamus.spring_university_project.services.VehicleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class VehicleController {
    VehicleService vehicleService = new VehicleService();
    @GetMapping("/vehicles/findAll")
    String findAll(){
        return vehicleService.findAll();
    }
    @GetMapping("/vehicles/findActive")
    String findActive(){
        return vehicleService.findActive();
    }
    @GetMapping("/vehicles/findById")
    @ResponseBody
    String findById(@RequestParam String id){
        return vehicleService.findById(id);
    }
    @GetMapping("/vehicles/findAvailableVehicles")
    String findAvailableVehicles(){
        return findAvailableVehicles();
    }
    @GetMapping("/vehicles/findRentedVehicles")
    String findRentedVehicles(){
        return vehicleService.findRentedVehicles();
    }
    @GetMapping("/vehicles/isAvailable")
    @ResponseBody
    boolean isAvailable(@RequestParam String id){
        return vehicleService.isAvailable(id);
    }
    @GetMapping("/vehicles/text")
    String test(){
        return "sigma2";
    }
}
