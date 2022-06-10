package com.spring.crud.demo.service;

import java.util.List;

import com.spring.crud.demo.models.Price;

public interface PriceService {
    List<Price> findAll();

    Price findById(int id);

    Price save(Price price);

    Price update(int id, Price price);

    void delete(int id);
}
