package com.epam.brest.summer.courses2019.dao;


import com.epam.brest.summer.courses2019.model.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Transactional
@Rollback
public class CarDaoJdbcImplTest {

    private static final String CAR_MODEL = "Mercedes";
    private static final String UPDATE_CAR_MODEL = "Volvo";
    private static final String CAR_NUMBER = "16-61 AA-1";
    private static final Integer LOAD_CAPACITY = 10;
    private static final String CAR_CHARACTERISTICS = "реф";
    private static final String CAR_DRIVER = "Скворцов С.С.";

    @Autowired
    CarDao carDao;

    @Test
    public void findAll() {
        List<Car> cars = carDao.findAll();
        assertNotNull(cars);
        assertTrue(cars.size() > 0);
    }

    @Test
    public void addCar() {
        Car testCar = new Car();
        testCar.setCarModel(CAR_MODEL);
        testCar.setCarNumber(CAR_NUMBER);
        testCar.setLoadCapacity(LOAD_CAPACITY);
        testCar.setCarCharacteristics(CAR_CHARACTERISTICS);
        testCar.setCarDriver(CAR_DRIVER);
        Car newCar = carDao.add(testCar);
        assertNotNull(newCar.getCarId());
    }

    @Test
    public void getCarById() {
        Car car = carDao.findById(1).get();
        assertNotNull(car);
        assertTrue(car.getCarId().equals(1));
        assertEquals("Mercedes", car.getCarModel());
        assertEquals("11-44 AA-1", car.getCarNumber());
        assertTrue(car.getLoadCapacity().equals(10));
        assertEquals("тент", car.getCarCharacteristics());
        assertEquals("Попов", car.getCarDriver());
    }


    @Test
    public void updateCar() {
        Car newCar = createCar();
        newCar = carDao.add(newCar);
        newCar.setCarModel(UPDATE_CAR_MODEL);
        carDao.update(newCar);
        Car updatedCar = carDao.findById(newCar.getCarId()).get();
        assertTrue(newCar.getCarId().equals(updatedCar.getCarId()));
        assertTrue(newCar.getCarModel().equals(updatedCar.getCarModel()));
    }

    @Test
    public void deleteCar() {
        Car car = createCar();
        car = carDao.add(car);
        List<Car> cars = carDao.findAll();
        int sizeBefore = cars.size();
        carDao.delete(car.getCarId());
        assertTrue((sizeBefore - 1) == carDao.findAll().size());
    }

    private Car createCar() {
        Car car = new Car();
        car.setCarModel(CAR_MODEL);
        car.setCarNumber(CAR_NUMBER);
        car.setLoadCapacity(LOAD_CAPACITY);
        car.setCarCharacteristics(CAR_CHARACTERISTICS);
        car.setCarDriver(CAR_DRIVER);
        return car;
    }

}
