package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class TripTest {


    private static final Integer TRIP_ID = 15;
    private static final Integer TRIP_DISTANCE = 2500;
    private static final LocalDate TRIP_DATE = LocalDate.of(2019, 8, 01);
    private static final Integer CAR_ID = 8;
    private static final Integer TRIP_STATUS_ID = 3;

    Trip trip = new Trip();

    @Test
    public void getTripId() {
        trip.setTripId(TRIP_ID);
        Assert.assertTrue(trip.getTripId().equals(TRIP_ID));
    }

    @Test
    public void getTripDistance() {
        trip.setDistance(TRIP_DISTANCE);
        Assert.assertTrue(trip.getDistance().equals(TRIP_DISTANCE));
    }
    @Test
    public void getTripDate() {
        trip.setDateTrip(TRIP_DATE);
        Assert.assertTrue(trip.getDateTrip().equals(TRIP_DATE));
    }
    @Test
    public void getTripStatus() {
        trip.setTripStatusId(TRIP_STATUS_ID);
        Assert.assertTrue(trip.getTripStatusId().equals(TRIP_STATUS_ID));
    }
    @Test
    public void getCarId() {
        trip.setCarId(CAR_ID);
        Assert.assertTrue(trip.getCarId().equals(CAR_ID));
    }

    @Test
    public void testToString() {
        trip.setTripId(TRIP_ID);
        trip.setDistance(TRIP_DISTANCE);
        trip.setDateTrip(TRIP_DATE);
        trip.setCarId(CAR_ID);
        trip.setTripStatusId(TRIP_STATUS_ID);

        String expected = "Trip{"
                + "tripId=" + TRIP_ID
                + ", dateTrip=" + TRIP_DATE
                + ", carId=" + CAR_ID
                + ", distance=" + TRIP_DISTANCE
                + ", tripStatusId=" + TRIP_STATUS_ID
                + "}";

        Assert.assertTrue(expected.equals(trip.toString()));

    }


}
