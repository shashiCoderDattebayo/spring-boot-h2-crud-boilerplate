package com.spring.crud.demo.dbModels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.spring.crud.demo.models.Vehicle;

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
public class Price {
    @Id
    @GeneratedValue
    private int id;
    private Vehicle.Type type;
    private int branchId;
    private int price;
}
