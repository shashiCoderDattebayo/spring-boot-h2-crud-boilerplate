package com.spring.crud.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.spring.crud.demo.models.Price;
import com.spring.crud.demo.repository.PriceRepository;
import com.spring.crud.demo.service.PriceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository repository;

    @Override
    public List<Price> findAll() {
        return repository.findAll().stream().map(Price::new).collect(Collectors.toList());
    }

    @Override
    public Price findById(int id) {
        return new Price(repository.findById(id).orElseThrow(
            () -> new NotFoundException("** Price not found for id :: " + id)));
    }

    @Override
    public Price save(Price price) {
        com.spring.crud.demo.dbModels.Price dbPrice = repository.save(price.toDbObject());
        return new Price(repository.save(dbPrice));
    }

    @Override
    public Price update(int id, Price price) {
        repository.findById(id).orElseThrow(
            () -> new NotFoundException("** Price not found for id :: " + id));

        return new Price(repository.save(price.toDbObject()));
    }

    @Override
    public void delete(int id) {
        repository.findById(id).ifPresent(price -> repository.delete(price));
    }
}
