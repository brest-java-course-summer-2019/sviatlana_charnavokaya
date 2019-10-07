package com.epam.brest.summer.courses2019.web_app.consumers;

import com.epam.brest.summer.courses2019.model.Car;
import com.epam.brest.summer.courses2019.model.stub.CarStub;
import com.epam.brest.summer.courses2019.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class CarRestConsumer implements CarService{

    private static final Logger LOGGER = LoggerFactory.getLogger(CarRestConsumer.class);

    private String url;

    private RestTemplate restTemplate;

    public CarRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Car> findAll() {
        LOGGER.debug("findAllCars()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/", List.class);
        return (List<Car>) responseEntity.getBody();
    }

    @Override
    public List<CarStub> findAllWithDistanceAndTrips() {
        LOGGER.debug("findAllWithDistanceAndTrips()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/with_distance_and_trips", List.class);
        return (List<CarStub>) responseEntity.getBody();
    }


    @Override
    public Car findById(Integer id) {
        LOGGER.debug("findById({})", id);
        ResponseEntity<Car> responseEntity = restTemplate.getForEntity(url + "/" + id, Car.class);
        return responseEntity.getBody();
    }

    @Override
    public void update(Car car) {
        LOGGER.debug("update({})", car);
        restTemplate.put(url, car);
    }


    @Override
    public void delete(Integer id) {
        LOGGER.debug("delete({})", id);
        restTemplate.delete(url + "/" + id);
    }


    @Override
    public Car add(Car car) {
        LOGGER.debug("add({})", car);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, car, Car.class);
        Object result = responseEntity.getBody();
        return (Car) result;
    }

}
