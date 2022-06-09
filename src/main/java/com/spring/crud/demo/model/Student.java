package com.spring.crud.demo.model;

import java.io.Serializable;
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
public class Student implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private int rollNo;
    private String firstName;
    private String lastName;
    private float marks;

}
