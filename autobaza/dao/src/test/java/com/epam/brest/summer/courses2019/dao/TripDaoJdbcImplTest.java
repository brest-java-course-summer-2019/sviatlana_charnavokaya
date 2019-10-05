package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Trip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Transactional
@Rollback
public class TripDaoJdbcImplTest {

    private static final LocalDate DATE_TRIP = LocalDate.of(2019, 9, 01);
    private static final LocalDate UPDATE_DATE_TRIP = LocalDate.of(2019, 9, 01);
    private static final Integer CAR_ID = 6;
    private static final Integer DISTANCE = 1201;
    private static final Integer TRIP_STATUS_ID = 1;

    @Autowired
    TripDao tripDao;

    @Test
    public void findAll() {
        List<Trip> trips = tripDao.findAll();
        assertNotNull(trips);
        assertTrue(trips.size() > 0);
    }

    @Test
    public void getTripById() {
        Trip trip = tripDao.findById(1).get();
        assertNotNull(trip);
        assertTrue(trip.getTripId().equals(1));
        assertTrue(trip.getDateTrip().equals(LocalDate.of(2019,8,01)));
        assertTrue(trip.getCarId().equals(2));
        assertTrue(trip.getDistance().equals(150));
        assertTrue(trip.getTripStatusId().equals(3));

    }


    @Test
    public void updateTrip() {
        Trip newTrip = createTrip();
        newTrip = tripDao.add(newTrip);
        newTrip.setDateTrip(UPDATE_DATE_TRIP);
        tripDao.update(newTrip);
        Trip updatedTrip = tripDao.findById(newTrip.getTripId()).get();
        assertTrue(newTrip.getTripId().equals(updatedTrip.getTripId()));
        assertTrue(newTrip.getDateTrip().equals(updatedTrip.getDateTrip()));
    }

    @Test
    public void deleteTrip() {
        Trip trip = createTrip();
        trip = tripDao.add(trip);
        List<Trip> trips = tripDao.findAll();
        int sizeBefore = trips.size();
        tripDao.delete(trip.getTripId());
        assertEquals((sizeBefore - 1), tripDao.findAll().size());
    }

    @Test
    public void addTrip() {
        Trip testTrip = createTrip();
        int sizeBefore = tripDao.findAll().size();
        Trip newTrip = tripDao.add(testTrip);
        assertNotNull(newTrip.getCarId());
        assertEquals((sizeBefore + 1), tripDao.findAll().size());
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
