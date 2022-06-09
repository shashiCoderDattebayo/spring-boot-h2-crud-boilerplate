package com.spring.crud.demo.controller;

import java.net.URI;
import java.util.List;

import com.spring.crud.demo.annotation.LogObjectAfter;
import com.spring.crud.demo.annotation.LogObjectBefore;
import com.spring.crud.demo.model.emp.Employee;
import com.spring.crud.demo.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @LogObjectAfter
    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        List<?> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }


    @LogObjectAfter
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Employee employee = service.findById(id);
        return ResponseEntity.ok().body(employee);
    }


    @LogObjectBefore
    @LogObjectAfter
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Employee employee) {
        Employee savedEmployee = service.save(employee);

//        return ResponseEntity.ok().body(savedEmployee);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/{id}")
            .buildAndExpand(savedEmployee.getId())
            .toUri();
        return ResponseEntity.created(uri).body(savedEmployee);
    }


    @LogObjectBefore
    @LogObjectAfter
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Employee employee) {
        Employee updatedEmployee = service.update(id, employee);
        return ResponseEntity.ok().body(updatedEmployee);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok().body("Deleted successfully...!");
    }
}

