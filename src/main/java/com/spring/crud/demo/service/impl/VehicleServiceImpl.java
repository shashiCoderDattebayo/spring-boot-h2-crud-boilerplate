package com.spring.crud.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.spring.crud.demo.models.Branch;
import com.spring.crud.demo.models.Vehicle;
import com.spring.crud.demo.repository.BranchRepository;
import com.spring.crud.demo.repository.VehicleRepository;
import com.spring.crud.demo.service.BranchService;
import com.spring.crud.demo.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository repository;
    @Autowired
    private BranchService branchService;

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
        branchService.findById(vehicle.getBranchId());
        return new Vehicle(repository.save(vehicle.toDbObject()));
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
