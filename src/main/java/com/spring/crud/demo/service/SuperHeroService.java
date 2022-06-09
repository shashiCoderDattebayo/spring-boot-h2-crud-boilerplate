package com.spring.crud.demo.service;

import java.util.List;

import com.spring.crud.demo.model.SuperHero;

public interface SuperHeroService {

    List<?> findAll();

    SuperHero findById(int id);

    SuperHero save(SuperHero superHero);

    SuperHero update(int id, SuperHero superHero);

    void delete(int id);
}
