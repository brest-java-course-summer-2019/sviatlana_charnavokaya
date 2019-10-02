package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Trip;
import com.epam.brest.summer.courses2019.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class TripRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripRestController.class);

    @Autowired
    private TripService service;

    @GetMapping(value = "/trips")
    public Collection<Trip> findAll() {
        LOGGER.debug("get all trips");
        return service.findAll();
    }

    @GetMapping(value = "/trips/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Trip findById(@PathVariable Integer id) {
        LOGGER.debug("find trip by id({})", id);
        return service.findById(id);
    }

    @PutMapping()
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@RequestBody Trip trip) {
        LOGGER.debug("update trip ({})", trip);
        service.update(trip);
    }

    @DeleteMapping(value = "/trips/{id}")
    public void delete(@PathVariable("id") int id) {
        LOGGER.debug("delete trip ({})", id);
        service.delete(id);
    }

    @PostMapping()
    public ResponseEntity<Trip> add(@RequestBody Trip trip) {

        LOGGER.debug("add trip({})", trip);
        Trip result = service.add(trip);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
