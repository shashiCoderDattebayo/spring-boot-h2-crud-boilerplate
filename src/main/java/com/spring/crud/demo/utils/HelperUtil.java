package com.spring.crud.demo.utils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import com.spring.crud.demo.models.Branch;
import com.spring.crud.demo.models.Price;
import com.spring.crud.demo.models.Vehicle;

public class HelperUtil {


    public static Supplier<List<Branch>> branchesSupplier = () ->
        Arrays.asList(
            new Branch(1, "Vasanth Vihar", "Delhi"),
            new Branch(2, "Cyber City", "Delhi"),
            new Branch(3, "IIT", "Delhi"),
            new Branch(4, "Old", "Delhi"),
            new Branch(5, "New", "Delhi")
        );

    public static Supplier<List<Vehicle>> vehicleSupplier = () ->
        Arrays.asList(
            new Vehicle(1, "1", Vehicle.Type.SEDAN),
            new Vehicle(2, "2", Vehicle.Type.HATCHBACK),
            new Vehicle(3, "3", Vehicle.Type.SEDAN),
            new Vehicle(4, "4", Vehicle.Type.HATCHBACK),
            new Vehicle(5, "5", Vehicle.Type.HATCHBACK)
        );

    public static Supplier<List<Price>> priceSupplier = () ->
        Arrays.asList(
            new Price(1, Vehicle.Type.SEDAN, 1, 1),
            new Price(2, Vehicle.Type.HATCHBACK, 1, 2),
            new Price(3, Vehicle.Type.SEDAN, 2, 3),
            new Price(4, Vehicle.Type.HATCHBACK, 2, 4),
            new Price(5, Vehicle.Type.SEDAN, 3, 5),
            new Price(6, Vehicle.Type.HATCHBACK, 3, 6),
            new Price(7, Vehicle.Type.SEDAN, 4, 7),
            new Price(8, Vehicle.Type.HATCHBACK, 4, 8),
            new Price(9, Vehicle.Type.SEDAN, 5, 9),
            new Price(10, Vehicle.Type.HATCHBACK, 5, 10)
        );

    private HelperUtil() {
    }
}
