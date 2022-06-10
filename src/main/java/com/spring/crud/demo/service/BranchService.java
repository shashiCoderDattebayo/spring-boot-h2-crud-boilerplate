package com.spring.crud.demo.service;

import java.util.List;

import com.spring.crud.demo.models.Branch;

public interface BranchService {
    List<Branch> findAll();

    Branch findById(int id);

    Branch save(Branch branch);

    Branch update(int id, Branch branch);

    void delete(int id);
}
