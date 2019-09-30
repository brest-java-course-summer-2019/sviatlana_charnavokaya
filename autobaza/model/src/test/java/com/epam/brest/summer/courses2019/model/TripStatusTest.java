package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.Test;

public class TripStatusTest {

    private static final Integer TRIP_STATUS_ID = 15;
    private static final String TRIP_STATUS_NAME = "в пути";

    TripStatus tripStatus = new TripStatus();

    @Test
    public void getTripStatusId() {
        tripStatus.setTripStatusId(TRIP_STATUS_ID);
        Assert.assertTrue(tripStatus.getTripStatusId().equals(TRIP_STATUS_ID));
    }

    @Test
    public void getTripStatusName() {
        tripStatus.setTripStatusName(TRIP_STATUS_NAME);
        Assert.assertTrue(tripStatus.getTripStatusName().equals(TRIP_STATUS_NAME));
    }

    @Test
    public void testToString() {
        tripStatus.setTripStatusId(TRIP_STATUS_ID);
        tripStatus.setTripStatusName(TRIP_STATUS_NAME);
        String expected = "TripStatus {"
                + "tripStatusId=" + TRIP_STATUS_ID
                + ", tripStatusName=" + TRIP_STATUS_NAME
                + "}";
        Assert.assertTrue(expected.equals(tripStatus.toString()));
    }
}
