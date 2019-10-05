package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Trip;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
@Transactional
@Rollback
public class TripServiceImplTest {

    private static final LocalDate DATE_TRIP = LocalDate.of(2019, 9, 01);
   // private static final LocalDate UPDATE_DATE_TRIP = LocalDate.of(2019, 9, 01);
    private static final Integer CAR_ID = 6;
    private static final Integer DISTANCE = 1201;
    private static final Integer TRIP_STATUS_ID = 1;

    @Autowired
    private TripService tripService;

    @Test
    void findAll() {
        List<Trip> trips = tripService.findAll();

        assertNotNull(trips);
        assertTrue(trips.size() > 0);
        assertFalse(trips.isEmpty());
    }

    @Test
    void findById() {
        int id = 1;
        Trip trip = tripService.findById(id);

        assertNotNull(trip);
        assertTrue(trip.getTripId().equals(1));
        assertTrue(trip.getDateTrip().equals(LocalDate.of(2019,8,01)));
        assertTrue(trip.getCarId().equals(2));
        assertTrue(trip.getDistance().equals(150));
        assertTrue(trip.getTripStatusId().equals(3));

    }

    @Test
    void update() {
        int id = 2;
        Trip trip = tripService.findById(id);
        trip.setDateTrip(DATE_TRIP);
        trip.setDistance(DISTANCE);
        tripService.update(trip);
        Trip updatedTrip = tripService.findById(id);
        assertNotNull(updatedTrip);
        assertEquals(trip.getDateTrip(), updatedTrip.getDateTrip());
        assertEquals(trip.getDistance(), updatedTrip.getDistance());
        assertEquals(trip.getTripStatusId(), updatedTrip.getTripStatusId());


    }

    @Test()
    void delete() {
        Trip trip = createTrip();
        tripService.add(trip);
        List<Trip> trips = tripService.findAll();
        int sizeBefore = trips.size();
        tripService.delete(trip.getTripId());
        assertEquals((sizeBefore - 1), tripService.findAll().size());
        assertThrows(RuntimeException.class, () -> tripService.findById(trip.getTripId()));
    }

    @Test
    public void add() {
        int sizeBefore = tripService.findAll().size();
        Trip trip = createTrip();
        Trip newTrip = tripService.add(trip);
        assertNotNull(newTrip.getTripId());
        assertEquals((sizeBefore + 1), tripService.findAll().size());
    }



    private Trip createTrip() {
        Trip trip = new Trip();
        trip.setDateTrip(DATE_TRIP);
        trip.setCarId(CAR_ID);
        trip.setDistance(DISTANCE);
        trip.setTripStatusId(TRIP_STATUS_ID);

        return trip;
    }
}
