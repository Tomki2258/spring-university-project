package com.tamus.spring_university_project.repositories.Jdbc;


import com.tamus.spring_university_project.db.JdbcConnectionManager;
import com.tamus.spring_university_project.models.Rental;
import com.tamus.spring_university_project.repositories.IRentalRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RentalJdbcRepository implements IRentalRepository {
    @Override
    public void fn() {

    }

    @Override
    public List<Rental> getRentals() {
        List<Rental> list = new ArrayList<>();
        String sql = "SELECT * FROM rental";

        try (Connection connection = JdbcConnectionManager.getInstance().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Rental rental = Rental.builder().id(rs.getString("id")).userID(rs.getString("user_id")).vehicleID(rs.getString("vehicle_id")).rentDate(rs.getString("rent_date")).returnDate(rs.getString("return_date")).build();

                list.add(rental);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while reading vehicles", e);
        }
        return list;
    }

    @Override
    public void add(Rental rental) {
        String UUID = java.util.UUID.randomUUID().toString();
        String sql = "INSERT INTO rental (id, vehicle_id, user_id, rent_date, return_date) VALUES (?, ?, ?, ?, NULL);";
        try (Connection connection = JdbcConnectionManager.getInstance().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, UUID);
            stmt.setString(2, rental.getVehicleId());
            stmt.setString(3, rental.getUserID());
            stmt.setString(4, rental.getRentDate());
            //stmt.setString(1, vehicle.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while saving vehicle", e);
        }
    }

    @Override
    public void returnVehicle(String userUD) {
        String sql = "UPDATE rental SET return_date = ? WHERE user_ID = ? AND return_date IS NULL;";
        try (Connection connection = JdbcConnectionManager.getInstance().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, String.valueOf(LocalDateTime.now()));
            stmt.setString(2, userUD);
            //stmt.setString(1, vehicle.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while saving vehicle", e);
        }
    }
}
