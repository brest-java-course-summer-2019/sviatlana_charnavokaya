package com.epam.brest.summer.courses2019.web_app.consumers;

import com.epam.brest.summer.courses2019.model.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TripRestConsumerTest {

    private static final DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final LocalDate DATE_TRIP = LocalDate.of(2019, 9, 01);
    private static final LocalDate START_DATE = LocalDate.of(2019, 9, 01);
    private static final LocalDate END_DATE = LocalDate.of(2019, 9, 06);

    private static final Integer DISTANCE_VALUE = 1201;
    private static final Integer ID_1 = 1;
    private static final Integer ID_2 = 2;

    private RestTemplate restTemplate;

    private String url = "/trips";

    private TripRestConsumer tripRestConsumer;

    @BeforeEach
    void setUp() {
        restTemplate = Mockito.mock(RestTemplate.class);
        tripRestConsumer = new TripRestConsumer(url, restTemplate);
    }


    @Test
    public void findAll() throws Exception {
        List<Trip> trips = new ArrayList<>();
        trips.add(createTrip(ID_1));
        trips.add(createTrip(ID_2));

        Mockito.when(restTemplate.getForEntity(url + "/", List.class))
                .thenReturn(new ResponseEntity<>(trips, HttpStatus.OK));

        List<Trip> expected = tripRestConsumer.findAll();

        assertNotNull(expected);
        assertEquals(expected, trips);

        Mockito.verify(restTemplate, Mockito.times(1)).getForEntity(url + "/", List.class);
    }


    @Test
    void findByDates() {

        List<Trip> trips = new ArrayList<>();
        trips.add(createTrip(ID_1));
        trips.add(createTrip(ID_2));

        String paramUrl = "?startDate=" + START_DATE.format(DATE_FORMATER) +
                "&endDate=" + END_DATE.format(DATE_FORMATER);

        Mockito.when(restTemplate.getForEntity(url + "/filter" + paramUrl, List.class))
                .thenReturn(new ResponseEntity<>(trips, HttpStatus.OK));

        List<Trip> expected = tripRestConsumer.findByDates(START_DATE, END_DATE);

        assertNotNull(expected);
        assertEquals(expected, trips);

        Mockito.verify(restTemplate, Mockito.times(1)).getForEntity(url + "/filter" + paramUrl, List.class);

    }


    @Test
    public void findById() throws Exception {

        Trip expected = createTrip(ID_1);

        Mockito.when(restTemplate.getForEntity(url + "/" + ID_1, Trip.class))
                .thenReturn(new ResponseEntity<>(createTrip(ID_1), HttpStatus.OK));

        Trip trip = tripRestConsumer.findById(ID_1);

        assertEquals(expected.getTripId(), trip.getTripId());
        assertEquals(expected.getDistance(), trip.getDistance());
        assertEquals(expected.getCarId(), trip.getCarId());
        assertEquals(expected.getDateTrip(), trip.getDateTrip());


        Mockito.verify(restTemplate, Mockito.times(1)).getForEntity(url + "/" + ID_1, Trip.class);

    }


    @Test
    void add() {
        Trip trip = createTrip(ID_1);

        Mockito.when(restTemplate.postForEntity(url, trip, Trip.class))
                .thenReturn(new ResponseEntity<>(trip, HttpStatus.OK));

        Trip expected = tripRestConsumer.add(trip);

        assertEquals(expected.getTripId(), trip.getTripId());
        assertEquals(expected.getDistance(), trip.getDistance());
        assertEquals(expected.getCarId(), trip.getCarId());
        assertEquals(expected.getDateTrip(), trip.getDateTrip());

        Mockito.verify(restTemplate, Mockito.times(1)).postForEntity(url, trip, Trip.class);
    }


    @Test
    public void update() throws Exception {
        Trip trip = createTrip(ID_1);

        tripRestConsumer.update(trip);
        Mockito.verify(restTemplate, Mockito.times(1)).put(url, trip);

    }


    @Test
    public void delete() throws Exception {

        tripRestConsumer.delete(ID_1);

        Mockito.verify(restTemplate, Mockito.times(1)).delete(url +"/" + ID_1);
    }




    private Trip createTrip(int index) {
        Trip trip = new Trip();
        trip.setTripId(index);
        trip.setDateTrip(DATE_TRIP);
        trip.setCarId(ID_1);
        trip.setDistance(DISTANCE_VALUE + index);
        trip.setTripStatusId(ID_1);

        return trip;
    }
}
