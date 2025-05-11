package com.tamus.spring_university_project.services;


import com.tamus.spring_university_project.User;
import com.tamus.spring_university_project.app.Main;
import com.tamus.spring_university_project.models.Rental;
import com.tamus.spring_university_project.models.Vehicle;
import com.tamus.spring_university_project.repositories.Jdbc.RentalJdbcRepository;
import com.tamus.spring_university_project.repositories.Json.RentalJsonRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class RentalService implements IRentalService{
    public List<Rental> rentalList;
    private RentalJdbcRepository rentalJdbcRepository;
    private RentalJsonRepository rentalJsonRepository;

    public RentalService()
    {
        if(Main.jsonMode){
            this.rentalJsonRepository = new RentalJsonRepository();
            rentalList = rentalJsonRepository.getRentals();
        }
        else{
            this.rentalJdbcRepository = new RentalJdbcRepository();
            rentalList = rentalJdbcRepository.getRentals();
        }
        //printRentals();
    }
    public void printRentals() {
        for (int i = 0; i < rentalList.size(); i++) {
            rentalList.get(i).describe();
        }
    }
    public boolean checkUserRent(User user) {
        String id = user.getId();

        Optional<Rental> found = rentalList.stream().filter(rental -> rental.getUserID().equals(id) && rental.getReturnDate().isEmpty()).findFirst();
        return found.isPresent();
    }
    public boolean isVehicleRented(Vehicle vehicle){
        Optional<Rental> found = rentalList.stream().filter(rental -> rental.getVehicleId().equals(vehicle.getId()) && !rental.getReturnDate().isEmpty()).findFirst();

        return found.isPresent();
    }
    public void returnRental(User user) {


        //rentalList.remove(toRemove);
    }
    public Optional<Rental> findByVehicleIdAndReturnDateIsNull(String vehicleId) {
        return rentalList.stream()
                .filter(r -> r.getVehicleId().equals(vehicleId))
                .filter(r -> r.getReturnDate() == null)
                .findFirst();
    }

    @Override
    public boolean isVehicleRented(String vehicleId) {
        return false;
    }

    @Override
    public Optional<Rental> findActiveRentalByVehicleId(String vehicleId) {
        return Optional.empty();
    }

    @Override
    public Rental rent(String vehicleId, String userId) {
        String rentalSize = String.valueOf(rentalList.size());
        String currentDate = LocalDateTime.now().toString();
        Rental rental = new Rental(rentalSize,
                userId,
                vehicleId,
                currentDate,
                "");
        if(Main.jsonMode) {
            rentalJsonRepository.add(rental);
        }
        else{
            rentalJdbcRepository.add(rental);
        }
        return rental;
    }

    @Override
    public boolean returnRental(String vehicleId, String userId) {
        return false;
    }

    @Override
    public List<Rental> findAll() {
        return List.of();
    }
}
