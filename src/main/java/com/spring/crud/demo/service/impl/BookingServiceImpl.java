package com.spring.crud.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.spring.crud.demo.models.Booking;
import com.spring.crud.demo.repository.BookingRepository;
import com.spring.crud.demo.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository repository;

    @Override
    public List<Booking> findAll() {
        return repository.findAll().stream().map(Booking::new).collect(Collectors.toList());
    }

    @Override
    public Booking findById(int id) {
        return new Booking(repository.findById(id).orElseThrow(
            () -> new NotFoundException("** Booking not found for id :: " + id)));
    }

    @Override
    public Booking save(Booking booking) {
        com.spring.crud.demo.dbModels.Booking dbBooking = repository.save(booking.toDbObject());
        return new Booking(repository.save(dbBooking));
    }

    @Override
    public Booking update(int id, Booking booking) {
        repository.findById(id).orElseThrow(
            () -> new NotFoundException("** Booking not found for id :: " + id));

        return new Booking(repository.save(booking.toDbObject()));
    }

    @Override
    public void delete(int id) {
        repository.findById(id).ifPresent(booking -> repository.delete(booking));
    }
}
