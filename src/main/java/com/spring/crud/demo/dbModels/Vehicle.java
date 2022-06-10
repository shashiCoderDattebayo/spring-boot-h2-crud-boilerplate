package com.spring.crud.demo.dbModels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Vehicle {
    @Id
    @GeneratedValue
    private int id;
    private String vehicleNumber;
    private com.spring.crud.demo.models.Vehicle.Type type;
}
