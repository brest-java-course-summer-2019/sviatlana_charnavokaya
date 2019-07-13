package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class TripTest {
    Trip trip = new Trip();

    @Test
    public void getTripId() {
        trip.setTripId(15);
        Assert.assertTrue(trip.getTripId().equals(15));
    }

    @Test
    public void getTripDistance() {
        trip.setDistance(2500);
        Assert.assertTrue(trip.getDistance().equals(2500));
    }
    @Test
    public void getTripDate() {
        trip.setDataTrip(LocalDate.of(2019, 8, 01));
        Assert.assertTrue(trip.getDataTrip().equals(LocalDate.of(2019, 8, 01)));
    }
    @Test
    public void getTripStatus() {
        trip.setTripStatus("В пути");
        Assert.assertTrue(trip.getTripStatus().equals("В пути"));
    }


}
