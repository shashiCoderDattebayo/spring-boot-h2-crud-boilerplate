package com.spring.crud.demo.service;

import java.util.List;

import com.spring.crud.demo.models.Vehicle;

public interface VehicleService {
    List<Vehicle> findAll();

    Vehicle findById(int id);

    Vehicle save(Vehicle vehicle);

    Vehicle update(int id, Vehicle vehicle);

    void delete(int id);
}
