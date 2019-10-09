package com.epam.brest.summer.courses2019.web_app.consumers;

import com.epam.brest.summer.courses2019.model.Trip;
import com.epam.brest.summer.courses2019.model.TripStatus;
import com.epam.brest.summer.courses2019.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class TripRestConsumer implements TripService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripRestConsumer.class);

    private String url;

    private RestTemplate restTemplate;

    public TripRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public Trip add(Trip trip) {
        LOGGER.debug("add({})", trip);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, trip, Trip.class);
        Object result = responseEntity.getBody();
        return (Trip) result;
    }

    @Override
    public void update(Trip trip) {
        LOGGER.debug("update({})", trip);
        restTemplate.put(url, trip);

    }

    @Override
    public void delete(Integer tripId) {
        LOGGER.debug("delete({})", tripId);
        restTemplate.delete(url + "/" + tripId);
    }

    @Override
    public List<Trip> findAll() {
        LOGGER.debug("findAllTrips()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/", List.class);
        return (List<Trip>) responseEntity.getBody();
    }

    @Override
    public Trip findById(Integer tripId) {

        LOGGER.debug("findById({})", tripId);
        ResponseEntity<Trip> responseEntity = restTemplate.getForEntity(url + "/" + tripId, Trip.class);
        return responseEntity.getBody();
    }

    @Override
    public List<TripStatus> findAllTripStatuses() {
        LOGGER.debug("findAllTripStatuses()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "Statuses/", List.class);
        return (List<TripStatus>) responseEntity.getBody();
    }

    @Override
    public TripStatus findTripStatusById(Integer tripStatusId) {
        return null;
    }
}
