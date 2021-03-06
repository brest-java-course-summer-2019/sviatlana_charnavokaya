package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Car;
import com.epam.brest.summer.courses2019.model.stub.CarStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
@Transactional
@Rollback
public class CarServiceImplTest {

    private static final String CAR_NUMBER = "11-44 AA-1";

    @Autowired
    private CarService carService;

    @Test
    void findAll() {
        List<Car> cars = carService.findAll();

        assertNotNull(cars);
        assertFalse(cars.isEmpty());
    }

    @Test
    public void findAllWithDistanceAndTrips() {
        List<CarStub> cars = carService.findAllWithDistanceAndTrips();
        assertNotNull(cars);
        assertTrue(cars.size() > 0);
    }


    @Test
    void findById() {
        int id = 1;
        Car car = carService.findById(id);

        assertNotNull(car);
        assertEquals(CAR_NUMBER, car.getCarNumber());
    }

    @Test
    void update() {
        int id = 2;
        Car car = new Car();
        car.setCarModel("Hyundai");
        car.setCarId(id);
        carService.update(car);
        car = carService.findById(id);

        assertNotNull(car);
        assertEquals("Hyundai", car.getCarModel());
    }

    @Test()
    void delete() {
        int id = 3;
        carService.delete(id);
        assertThrows(RuntimeException.class, () -> carService.findById(id));
    }

    @Test
    public void add() {
        int sizeBefore = carService.findAll().size();
        Car car = createCar();
        Car newCar = carService.add(car);
        assertNotNull(newCar.getCarId());
        assertEquals((sizeBefore + 1), carService.findAll().size());
    }


    private Car createCar() {
        Car car = new Car();
        car.setCarModel("Mercedes");
        car.setCarNumber("55-44 AB-7");
        car.setLoadCapacity(10);
        car.setCarCharacteristics("ref");
        car.setCarDriver("Скворцов С.С.");
        return car;
    }

}
