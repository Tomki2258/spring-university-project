package com.tamus.spring_university_project.repositories;



import com.tamus.spring_university_project.User;
import com.tamus.spring_university_project.models.Vehicle;

import java.io.IOException;
import java.util.List;

public interface IVehicleRepository {
    void rentVehicle(int index, User user);

    void returnVehicle(int index);

    void getVehicles(List <Vehicle> list);

    void save() throws IOException;

    void loadCSV();

    boolean equals(Vehicle a,Vehicle b);
    void addVehicle(Vehicle vehicle);
    void removeVehicle(int index);
    Vehicle getVehicle(int index);
    void getAvailableVehicles(List <Vehicle> vehicles);
    void loadJson();
    void saveJson();
}
