package com.tamus.spring_university_project.repositories.Json;

import com.google.gson.reflect.TypeToken;
import com.tamus.spring_university_project.JsonFileStorage;
import com.tamus.spring_university_project.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehiclesJsonRepository {
    private final String VEHICLES_PATH = "src/data/vehicles.json";
    private final JsonFileStorage storage =
            new JsonFileStorage<>(VEHICLES_PATH, new TypeToken<List<Vehicle>>(){}.getType());
    private final List<Vehicle> vehicles;

    public VehiclesJsonRepository() {
        this.vehicles = new ArrayList<>(storage.load());

        for (Vehicle vehicle:vehicles){
            vehicle.Describe();
        }
    }
    public List<Vehicle> getVehicles(){
        return vehicles;
    }
    public void save(List<Vehicle> vehicles){
        storage.save(vehicles);
        System.out.println("save");
    }
}
