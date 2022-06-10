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
            new Branch( "Vasanth Vihar", "Delhi"),
            new Branch( "Cyber City", "Delhi"),
            new Branch( "IIT", "Delhi"),
            new Branch( "Old", "Delhi"),
            new Branch( "New", "Delhi")
        );

    public static Supplier<List<Vehicle>> vehicleSupplier = () ->
        Arrays.asList(
            new Vehicle("1", Vehicle.Type.SEDAN, 1),
            new Vehicle("2", Vehicle.Type.HATCHBACK, 1),
            new Vehicle("3", Vehicle.Type.SEDAN, 1),
            new Vehicle("4", Vehicle.Type.HATCHBACK, 1),
            new Vehicle("5", Vehicle.Type.HATCHBACK, 1)
        );

    public static Supplier<List<Price>> priceSupplier = () ->
        Arrays.asList(
            new Price(Vehicle.Type.SEDAN, 1, 1),
            new Price(Vehicle.Type.HATCHBACK, 1, 2)
        );

    private HelperUtil() {
    }
}
