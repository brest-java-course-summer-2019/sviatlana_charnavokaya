package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Car;
import com.epam.brest.summer.courses2019.model.stub.CarStub;

import java.util.List;


/**
 * Car Service Interface.
 */
public interface CarService {

    /**
     * Add new car.
     *
     * @param car Car.
     * @return car.
     */
    Car add(Car car);

    /**
     * Update car.
     *
     * @param car car
     */
    void update(Car car);

    /**
     * Delete Car.
     *
     * @param carId car id
     */
    void delete(Integer carId);

    /**
     * Find all cars stream.
     *
     * @return cars.
     */
    List<Car> findAll();

    /**
     * Get all cars with number of trips and total distance by car.
     *
     * @return car list.
     */
    List<CarStub> findAllWithDistanceAndTrips();

    /**
     * Find Car By Id.
     *
     * @param carId id
     * @return Car
     */
    Car findById(Integer carId);

}
