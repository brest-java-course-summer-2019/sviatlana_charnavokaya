package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao {

    /**
     *
     * @param car
     * @return
     */
    Car add(Car car);

    void update(Car car);

    void delete(Integer carId);

    List<Car> findAll();

    Optional<Car> findById(Integer carId);

}
