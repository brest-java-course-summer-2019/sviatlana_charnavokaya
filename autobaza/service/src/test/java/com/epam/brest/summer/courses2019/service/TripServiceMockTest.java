package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.TripDao;
import com.epam.brest.summer.courses2019.model.Trip;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class TripServiceMockTest {

    private static final LocalDate DATE_TRIP = LocalDate.of(2019, 9, 01);
    private static final LocalDate UPDATE_DATE_TRIP = LocalDate.of(2019, 9, 01);
    private static final Integer CAR_ID = 6;
    private static final Integer DISTANCE = 1201;
    private static final Integer TRIP_STATUS_ID = 1;

    @Mock
    private TripDao tripDao;

    @Captor
    private ArgumentCaptor<Trip> tripCaptor;

    @InjectMocks
    private TripServiceImpl tripService;

    @AfterEach
    void after() {
        Mockito.verifyNoMoreInteractions(tripDao);
    }

    @Test
    void findAll() {

        Mockito.when(tripDao.findAll()).thenReturn(Collections.singletonList(createTrip()));

        List<Trip> result = tripService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());

        Mockito.verify(tripDao).findAll();
    }

    @Test
    void findById() {

        int id = 1;

        Mockito.when(tripDao.findById(id)).thenReturn(Optional.of(createTrip()));

        Trip trip = tripService.findById(id);

        assertNotNull(trip);
        assertEquals(DATE_TRIP, trip.getDateTrip());
        assertEquals(TRIP_STATUS_ID, trip.getTripStatusId());
        assertEquals(CAR_ID, trip.getCarId());

        Mockito.verify(tripDao).findById(id);
    }

    @Test
    public void add() {

        Trip createdTrip = createTrip();

        Mockito.when(tripDao.add(createdTrip)).thenReturn(createdTrip);

        Trip testedTrip = tripService.add(createdTrip);

        assertNotNull(testedTrip);
        assertEquals(DATE_TRIP, testedTrip.getDateTrip());
        assertEquals(TRIP_STATUS_ID, testedTrip.getTripStatusId());
        assertEquals(CAR_ID, testedTrip.getCarId());

        Mockito.verify(tripDao).add(createdTrip);
    }

    @Test
    void update() {

        tripService.update(createTrip());

        Mockito.verify(tripDao).update(tripCaptor.capture());

        Trip trip = tripCaptor.getValue();
        assertNotNull(trip);
        assertEquals(DATE_TRIP, trip.getDateTrip());
        assertEquals(TRIP_STATUS_ID, trip.getTripStatusId());
        assertEquals(CAR_ID, trip.getCarId());
    }

    @Test
    void delete() {

        int id = 3;

        tripService.delete(id);

        Mockito.verify(tripDao).delete(id);
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
