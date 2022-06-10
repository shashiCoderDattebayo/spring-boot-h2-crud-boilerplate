package com.spring.crud.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.spring.crud.demo.models.Branch;
import com.spring.crud.demo.repository.BranchRepository;
import com.spring.crud.demo.service.BranchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository repository;

    @Override
    public List<Branch> findAll() {
        List<Branch> branches = repository.findAll().stream().map(Branch::new).collect(
            Collectors.toList());
        log.info("Got branches:: " + branches);
        return branches;
    }

    @Override
    public Branch findById(int id) {
        return new Branch(repository.findById(id).orElseThrow(
            () -> new NotFoundException("** Branch not found for id :: " + id)));
    }

    @Override
    public Branch save(Branch branch) {
        log.info("save request " + branch);
        com.spring.crud.demo.dbModels.Branch dbBranch = repository.save(branch.toDbObject());
        log.info("save dbbranch " + dbBranch);
        com.spring.crud.demo.dbModels.Branch savedBranch = repository.save(dbBranch);
        log.info("saved dbbranch " + savedBranch);
        Branch foundBranch = findById(savedBranch.getId());
        log.info("found branch " + foundBranch);
        return new Branch(savedBranch);
    }

    @Override
    public Branch update(int id, Branch branch) {
        repository.findById(id).orElseThrow(
            () -> new NotFoundException("** Branch not found for id :: " + id));

        return new Branch(repository.save(branch.toDbObject()));
    }

    @Override
    public void delete(int id) {
        repository.findById(id).ifPresent(branch -> repository.delete(branch));
    }
}
