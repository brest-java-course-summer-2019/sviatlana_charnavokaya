package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Car;

import java.util.List;
import java.util.Optional;

/**
 * Car DAO Interface.
 */
public interface CarDao {

    /**
     * Persist new car.
     *
     * @param car new car
     * @return new car object.
     */
    Car add(Car car);

    /**
     * Update car.
     *
     * @param car car
     */
    void update(Car car);

    /**
     * Delete car with specified id.
     *
     * @param carId car id
     */
    void delete(Integer carId);


    /**
     * Get cars.
     *
     * @return cars list.
     */
    List<Car> findAll();

    /**
     * Get Car By Id.
     *
     * @param carId carID
     * @return Car
     */
    Optional<Car> findById(Integer carId);

}
