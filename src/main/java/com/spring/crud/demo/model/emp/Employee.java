package com.spring.crud.demo.model.emp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private int age;

    @Column(name = "no_of_childrens")
    private int noOfChildrens;
    private boolean spouse;

    @JsonManagedReference
    @OneToOne(cascade = {
        CascadeType.MERGE,
        CascadeType.PERSIST,
        CascadeType.REMOVE
    })
    @JoinColumn(name = "address")
    private Address address;


    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee",
        cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REMOVE
        })
    private List<PhoneNumber> phoneNumbers;


    @ElementCollection
    @CollectionTable(name = "hobbies", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "hobby")
    private List<String> hobbies = new ArrayList<>();

}

