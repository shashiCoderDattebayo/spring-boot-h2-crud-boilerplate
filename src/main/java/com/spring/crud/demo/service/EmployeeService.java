package com.spring.crud.demo.service;

import java.util.List;

import com.spring.crud.demo.model.emp.Employee;

public interface EmployeeService {

    List<?> findAll();

    Employee findById(int id);

    Employee save(Employee superHero);

    Employee update(int id, Employee employee);

    void delete(int id);

}
