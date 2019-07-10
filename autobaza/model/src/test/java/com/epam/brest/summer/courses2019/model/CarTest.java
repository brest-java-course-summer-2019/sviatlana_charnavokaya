package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class CarTest {

    Car car = new Car();

    @Test
    public void getCarId() {
        car.setCarId(15);
        Assert.assertTrue(car.getCarId().equals(15));
    }

    @Test
    public void getCarModel() {
        car.setCarModel("Mersedes");
        Assert.assertTrue(car.getCarModel().equals("Mersedes"));
    }

    @Test
    public void getFixed() {
        car.setFixed(true);
        Assert.assertTrue(car.getFixed().equals(true));
    }

}
