package com.spring.crud.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.spring.crud.demo.models.Vehicle;
import com.spring.crud.demo.repository.VehicleRepository;
import com.spring.crud.demo.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository repository;

    @Override
    public List<Vehicle> findAll() {
        return repository.findAll().stream().map(Vehicle::new).collect(Collectors.toList());
    }

    @Override
    public Vehicle findById(int id) {
        return new Vehicle(repository.findById(id).orElseThrow(
            () -> new NotFoundException("** Vehicle not found for id :: " + id)));
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        com.spring.crud.demo.dbModels.Vehicle dbVehicle = repository.save(vehicle.toDbObject());
        return new Vehicle(repository.save(dbVehicle));
    }

    @Override
    public Vehicle update(int id, Vehicle vehicle) {
        repository.findById(id).orElseThrow(
            () -> new NotFoundException("** Vehicle not found for id :: " + id));

        return new Vehicle(repository.save(vehicle.toDbObject()));
    }

    @Override
    public void delete(int id) {
        repository.findById(id).ifPresent(vehicle -> repository.delete(vehicle));
    }
}
