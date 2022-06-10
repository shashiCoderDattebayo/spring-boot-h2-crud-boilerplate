package com.spring.crud.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.spring.crud.demo.models.Booking;
import com.spring.crud.demo.repository.BookingRepository;
import com.spring.crud.demo.service.BookingService;
import com.spring.crud.demo.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository repository;
    @Autowired
    private VehicleService vehicleService;

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
        vehicleService.findById(booking.getVehicleId());
        for (Booking bookingSaved: this.findAll()) {
            if ((bookingSaved.getStartTime() <= booking.getStartTime()) && (bookingSaved.getEndTime() > booking.getStartTime())) {
                throw new NotFoundException("** Booking already exists :: ") ;
            } else if((bookingSaved.getStartTime() <= booking.getEndTime()) && (bookingSaved.getEndTime() > booking.getEndTime())) {
                throw new NotFoundException("** Booking already exists :: ") ;
            }
        }
        return new Booking(repository.save(booking.toDbObject()));
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
