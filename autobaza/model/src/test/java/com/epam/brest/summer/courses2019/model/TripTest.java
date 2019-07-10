package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.Test;

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
        trip.setTripId(15);
        Assert.assertTrue(trip.getTripId().equals(15));
    }
    @Test
    public void getTripStatus() {
        trip.setTripId(15);
        Assert.assertTrue(trip.getTripId().equals(15));
    }


}
