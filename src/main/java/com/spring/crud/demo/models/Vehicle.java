package com.spring.crud.demo.models;

import lombok.Getter;

@Getter
public class Vehicle {
    public enum Type {
        None,
        SEDAN,
        HATCHBACK
    }
    private final int id;
    private final String vehicleNumber;
    private final Type type;

    public Vehicle(int id, String vehicleNumber, Type type) {
        validate(id, vehicleNumber, type);
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.type = type;
    }

    private void validate(int id, String vehicleNumber, Type type) {
        // Add validations
    }

    public Vehicle(com.spring.crud.demo.dbModels.Vehicle vehicle) {
        this(vehicle.getId(), vehicle.getVehicleNumber(), vehicle.getType());
    }

    public com.spring.crud.demo.dbModels.Vehicle toDbObject() {
        return com.spring.crud.demo.dbModels.Vehicle.builder().vehicleNumber(vehicleNumber).type(type).build();
    }

    @Override
    public String toString() {
        return "Vehicle{" +
            "id=" + id +
            ", vehicleNumber='" + vehicleNumber + '\'' +
            ", type=" + type.toString() +
            '}';
    }
}
