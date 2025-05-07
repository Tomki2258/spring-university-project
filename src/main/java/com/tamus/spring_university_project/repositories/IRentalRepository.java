package com.tamus.spring_university_project.repositories;


import com.tamus.spring_university_project.models.Rental;

import java.util.List;

public interface IRentalRepository {
    void fn();
    List<Rental> getRentals();
    void add(Rental rental);
    void returnVehicle(String userUD);
}
