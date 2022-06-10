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
public class Booking {
    @Id
    @GeneratedValue
    private int id;

    private int vehicleId;
    private int startTime;
    private int endTime;
}
