package com.spring.crud.demo.models;

import lombok.Getter;

@Getter
public class Branch {
    private final int id;
    private final String branchName;
    private final String city;

    public Branch(int id, String branchName, String city) {
        validate(id, branchName, city);
        this.id = id;
        this.branchName = branchName;
        this.city = city;
    }

    private void validate(int id, String branchName, String city) {
    }

    public Branch(com.spring.crud.demo.dbModels.Branch branch) {
        this(branch.getId(), branch.getBranchName(), branch.getCity());
    }

    public com.spring.crud.demo.dbModels.Branch toDbObject() {
        return com.spring.crud.demo.dbModels.Branch.builder().branchName(branchName).city(city).build();
    }

    @Override
    public String toString() {
        return "Branch{" +
            "id=" + id +
            ", branchName='" + branchName + '\'' +
            ", city='" + city + '\'' +
            '}';
    }
}
