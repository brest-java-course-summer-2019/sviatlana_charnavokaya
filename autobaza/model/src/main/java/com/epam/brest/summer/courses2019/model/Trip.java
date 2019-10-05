package com.epam.brest.summer.courses2019.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * POJO Trip for model.
 */
public class Trip {

    /**
     * Trip Id.
     */
    private Integer tripId;

    /**
     * Trip Date.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateTrip;

    /**
     * Trip CarId.
     */
    private Integer carId;

    /**
     * Trip Distance.
     */
    private Integer distance;
    /**
     * Trip TripStatusId.
     */
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
