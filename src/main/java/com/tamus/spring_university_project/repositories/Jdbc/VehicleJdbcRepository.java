package com.tamus.spring_university_project.repositories.Jdbc;

import java.sql.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tamus.spring_university_project.db.JdbcConnectionManager;
import com.tamus.spring_university_project.models.Vehicle;
import com.tamus.spring_university_project.repositories.VehicleRepository;
import com.tamus.spring_university_project.services.IVehicleService;


public class VehicleJdbcRepository implements IVehicleService {

    private final Gson gson = new Gson();

    @Override
    public List<Vehicle> findAll() {
        List<Vehicle> list = new ArrayList<>();
        String sql = "SELECT * FROM vehicle";
        try (Connection connection = JdbcConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String attrJson = rs.getString("attributes");
                Map<String, Object> attributes = gson.fromJson(attrJson, new TypeToken<Map<String, Object>>(){}.getType());

                Vehicle vehicle = Vehicle.builder()
                        .id(rs.getString("id"))
                        .category(rs.getString("category"))
                        .brand(rs.getString("brand"))
                        .model(rs.getString("model"))
                        .year(rs.getInt("year"))
                        .plate(rs.getString("plate"))
                        .price((int) rs.getDouble("price"))
                        .attributes(attributes != null ? attributes : new HashMap<>())
                        .build();
                list.add(vehicle);

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while reading vehicles", e);
        }
        return list;
    }

    @Override
    public List<Vehicle> findAllActive() {
        List<Vehicle> list = new ArrayList<>();
        String sql = "SELECT v.* FROM vehicle v JOIN rental r ON v.id = r.vehicle_id WHERE r.return_date IS NULL";
        try (Connection connection = JdbcConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String attrJson = rs.getString("attributes");
                Map<String, Object> attributes = gson.fromJson(attrJson, new TypeToken<Map<String, Object>>(){}.getType());

                Vehicle vehicle = Vehicle.builder()
                        .id(rs.getString("id"))
                        .category(rs.getString("category"))
                        .brand(rs.getString("brand"))
                        .model(rs.getString("model"))
                        .year(rs.getInt("year"))
                        .plate(rs.getString("plate"))
                        .price((int) rs.getDouble("price"))
                        .attributes(attributes != null ? attributes : new HashMap<>())
                        .build();
                list.add(vehicle);

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while reading vehicles", e);
        }
        return list;
    }

    @Override
    public Optional<Vehicle> findById(String id) {
        String sql = "SELECT * FROM vehicle WHERE id = ?";
        try (Connection connection = JdbcConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String attrJson = rs.getString("attributes");
                    Map<String, Object> attributes = gson.fromJson(attrJson, new TypeToken<Map<String, Object>>() {}.getType());

                    Vehicle vehicle = Vehicle.builder()
                            .id(rs.getString("id"))
                            .category(rs.getString("category"))
                            .brand(rs.getString("brand"))
                            .model(rs.getString("model"))
                            .year(rs.getInt("year"))
                            .plate(rs.getString("plate"))
                            .price((int) rs.getDouble("price"))
                            .attributes(attributes != null ? attributes : new HashMap<>())
                            .build();
                    return Optional.of(vehicle);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while reading vehicle", e);
        }
        return Optional.empty();
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        return null;
    }

    @Override
    public List<Vehicle> findAvailableVehicles() {
        List<Vehicle> list = new ArrayList<>();
        String sql = "SELECT v.* FROM vehicle v JOIN rental r ON v.id = r.vehicle_id WHERE r.return_date IS NULL";
        try (Connection connection = JdbcConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String attrJson = rs.getString("attributes");
                Map<String, Object> attributes = gson.fromJson(attrJson, new TypeToken<Map<String, Object>>(){}.getType());

                Vehicle vehicle = Vehicle.builder()
                        .id(rs.getString("id"))
                        .category(rs.getString("category"))
                        .brand(rs.getString("brand"))
                        .model(rs.getString("model"))
                        .year(rs.getInt("year"))
                        .plate(rs.getString("plate"))
                        .price((int) rs.getDouble("price"))
                        .attributes(attributes != null ? attributes : new HashMap<>())
                        .build();
                list.add(vehicle);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while reading vehicles", e);
        }
        return list;
    }

    @Override
    public List<Vehicle> findRentedVehicles() {
        List<Vehicle> list = new ArrayList<>();
        String sql = "SELECT v.* FROM vehicle v JOIN rental r ON v.id = r.vehicle_id WHERE r.return_date IS NOT NULL";
        try (Connection connection = JdbcConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String attrJson = rs.getString("attributes");
                Map<String, Object> attributes = gson.fromJson(attrJson, new TypeToken<Map<String, Object>>(){}.getType());

                Vehicle vehicle = Vehicle.builder()
                        .id(rs.getString("id"))
                        .category(rs.getString("category"))
                        .brand(rs.getString("brand"))
                        .model(rs.getString("model"))
                        .year(rs.getInt("year"))
                        .plate(rs.getString("plate"))
                        .price((int) rs.getDouble("price"))
                        .attributes(attributes != null ? attributes : new HashMap<>())
                        .build();
                list.add(vehicle);

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while reading vehicles", e);
        }
        return list;
    }

    @Override
    public boolean isAvailable(String vehicleId) {
        String sql = "SELECT EXISTS (SELECT 1 FROM rental WHERE vehicle_id = ? AND return_date IS NULL ) AS is_rented;";
        try (Connection connection = JdbcConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, vehicleId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String result =rs.getString("is_rented");
                    if(result.equals("f")){
                        return false;
                    }else if(result.equals("t")){
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while reading vehicle", e);
        }
        return false;
    }

    public Vehicle addToDatabase(Vehicle vehicle) {
//        if (vehicle.getId() == null || vehicle.getId().isBlank()) {
//            vehicle.setId(UUID.randomUUID().toString());
//        } else {
//            deleteById(vehicle.getId());
//        }

        String sql = "INSERT INTO vehicle (id, category, brand, model, year, plate, price, attributes) VALUES (?, ?, ?, ?, ?, ?, ?, ?::jsonb)";
        try (Connection connection = JdbcConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getId());
            stmt.setString(2, vehicle.getCategory());
            stmt.setString(3, vehicle.getBrand());
            stmt.setString(4, vehicle.getModel());
            stmt.setInt(5, vehicle.getYear());
            stmt.setString(6, vehicle.getPlate());
            stmt.setDouble(7, vehicle.getPrice());
            stmt.setString(8, gson.toJson(vehicle.getAttributes()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while saving vehicle", e);
        }
        return vehicle;
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM vehicle WHERE id = ?";
        try (Connection connection = JdbcConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while deleting vehicle", e);
        }
    }
    public void save() {

    }
}