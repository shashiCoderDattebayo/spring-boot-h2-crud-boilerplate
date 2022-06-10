package com.spring.crud.demo.models;

import lombok.Getter;

@Getter
public class Price {
    private final int id;
    private final Vehicle.Type type;
    private final int branchId;
    private final int price;

    private Price(int id, Vehicle.Type type, int branchId, int price) {
        validate(id, type, branchId, price);
        this.id = id;
        this.type = type;
        this.branchId = branchId;
        this.price = price;
    }

    public Price(Vehicle.Type type, int branchId, int price) {
        this(-1, type, branchId, price);
    }

    private void validate(int id, Vehicle.Type type, int branchId, int price) {
    }

    public Price(com.spring.crud.demo.dbModels.Price price) {
        this(price.getId(), price.getType(), price.getBranchId(), price.getPrice());
    }

    public com.spring.crud.demo.dbModels.Price toDbObject() {
        return com.spring.crud.demo.dbModels.Price.builder().type(type).branchId(branchId).price(price).build();
    }

    @Override
    public String toString() {
        return "Price{" +
            "id=" + id +
            ", type=" + type +
            ", branchId=" + branchId +
            ", price=" + price +
            '}';
    }
}
