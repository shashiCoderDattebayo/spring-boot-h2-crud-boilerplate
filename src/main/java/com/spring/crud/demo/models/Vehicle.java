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
    private final int branchId;

    private Vehicle(int id, String vehicleNumber, Type type, int branchId) {
        validate(id, vehicleNumber, type);
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.branchId = branchId;
    }

    public Vehicle(String vehicleNumber, Type type, int branchId) {
        this(-1, vehicleNumber, type, branchId);
    }

    private void validate(int id, String vehicleNumber, Type type) {
        // Add validations
    }

    public Vehicle(com.spring.crud.demo.dbModels.Vehicle vehicle) {
        this(vehicle.getId(), vehicle.getVehicleNumber(), vehicle.getType(), vehicle.getBranchId());
    }

    public com.spring.crud.demo.dbModels.Vehicle toDbObject() {
        return com.spring.crud.demo.dbModels.Vehicle.builder().vehicleNumber(vehicleNumber).branchId(branchId).type(type).build();
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
