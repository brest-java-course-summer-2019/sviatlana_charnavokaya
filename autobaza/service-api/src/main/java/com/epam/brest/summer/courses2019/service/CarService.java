package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Car;

import java.util.List;


/**
 * Car Service Interface.
 */
public interface CarService {

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
     * Find Car By Id.
     *
     * @param carId id
     * @return Car
     */
    Car findById(Integer carId);

}
