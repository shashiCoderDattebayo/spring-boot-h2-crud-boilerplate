package com.spring.crud.demo.service;

import java.util.List;

import com.spring.crud.demo.models.Booking;

public interface BookingService {
    List<Booking> findAll();

    Booking findById(int id);

    Booking save(Booking booking);

    Booking update(int id, Booking booking);

    void delete(int id);
}
