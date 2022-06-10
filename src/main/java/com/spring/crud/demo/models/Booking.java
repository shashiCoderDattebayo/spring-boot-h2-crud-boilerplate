package com.spring.crud.demo.models;

import lombok.Getter;

@Getter
public class Booking {
    private final int id;
    private final String vehicleId;
    private final String startTime;
    private final String endTime;

    public Booking(int id, String vehicleId, String startTime, String endTime) {
        validate(id, vehicleId, startTime, endTime);
        this.id = id;
        this.vehicleId = vehicleId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private void validate(int id, String vehicleId, String startTime, String endTime) {
    }

    public Booking(com.spring.crud.demo.dbModels.Booking booking) {
        this(booking.getId(), booking.getVehicleId(), booking.getStartTime(), booking.getEndTime());
    }

    public com.spring.crud.demo.dbModels.Booking toDbObject() {
        return com.spring.crud.demo.dbModels.Booking.builder().vehicleId(vehicleId).startTime(startTime).endTime(endTime).build();
    }

    @Override
    public String toString() {
        return "Booking{" +
            "id=" + id +
            ", vehicleId='" + vehicleId + '\'' +
            ", startTime='" + startTime + '\'' +
            ", endTime='" + endTime + '\'' +
            '}';
    }
}
