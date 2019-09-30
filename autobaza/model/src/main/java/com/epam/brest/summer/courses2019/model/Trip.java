package com.epam.brest.summer.courses2019.model;

import java.time.LocalDate;

public class Trip {

    private Integer tripId;
    private LocalDate dateTrip;
    private Integer carId;
    private Integer distance;
    private Integer tripStatusId;

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public LocalDate getDateTrip() {
        return dateTrip;
    }

    public void setDateTrip(LocalDate dateTrip) {
        this.dateTrip = dateTrip;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getTripStatusId() {
        return tripStatusId;
    }

    public void setTripStatusId(Integer tripStatusId) {
        this.tripStatusId = tripStatusId;
    }

    @Override
    public String toString() {
        return "Trip{"
                + "tripId=" + tripId
                + ", dateTrip=" + dateTrip
                + ", carId=" + carId
                + ", distance=" + distance
                + ", tripStatusId=" + tripStatusId
                 + "}";
    }
}
