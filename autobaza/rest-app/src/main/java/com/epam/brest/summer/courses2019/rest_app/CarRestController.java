package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Car;
import com.epam.brest.summer.courses2019.model.stub.CarStub;
import com.epam.brest.summer.courses2019.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class CarRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarRestController.class);

    @Autowired
    private CarService service;

   @GetMapping(value = "/cars")
    public Collection<Car> findAll() {
        LOGGER.debug("get all cars");
        return service.findAll();
    }

    @GetMapping(value = "/cars/with_distance_and_trips")
    public Collection<CarStub> findAllStubs() {
        LOGGER.debug("get all cars stubs");
        return service.findAllWithDistanceAndTrips();
    }

    @GetMapping(value = "/cars/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Car findById(@PathVariable Integer id) {
        LOGGER.debug("find car by id({})", id);
        return service.findById(id);
    }

    @PutMapping(value = "/cars")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@RequestBody Car car) {
        LOGGER.debug("update car ({})", car);
        service.update(car);
    }

    @DeleteMapping(value = "/cars/{id}")
    public void delete(@PathVariable("id") int id) {
        LOGGER.debug("delete car ({})", id);
        service.delete(id);
    }

    @PostMapping(value = "/cars")
    public ResponseEntity<Car> add(@RequestBody Car car) {

        LOGGER.debug("add car({})", car);
        Car result = service.add(car);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
