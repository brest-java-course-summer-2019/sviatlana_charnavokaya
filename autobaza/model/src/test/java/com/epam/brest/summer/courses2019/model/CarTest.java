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
    public void getCarNunber() {
        car.setCarNumber("44-72 AA-1");
        Assert.assertTrue(car.getCarNumber().equals("44-72 AA-1"));
    }

    @Test
    public void getLoadCapacity() {
        car.setLoadCapacity(20);
        Assert.assertTrue(car.getLoadCapacity().equals(20));
    }

    @Test
    public void getCarCharacteristics() {
        car.setCarCharacteristics("Тент");
        Assert.assertTrue(car.getCarCharacteristics().equals("Тент"));
    }

    @Test
    public void getCarDriver() {
        car.setCarDriver("Петров Петр Петрович");
        Assert.assertTrue(car.getCarDriver().equals("Петров Петр Петрович"));
    }

    @Test
    public void testToString() {
        car.setCarId(15);
        car.setCarModel("Mersedes");
        car.setCarNumber("44-72 AA-1");
        car.setLoadCapacity(20);
        car.setCarCharacteristics("Тент");
        car.setCarDriver("Петров Петр Петрович");
        String expected = "Car {"
                + "carId=" + 15
                + ", carModel='" + "Mersedes"
                + ", carNumber='" + "44-72 AA-1"
                + ", loadCapacity=" + 20
                + ", carCharacteristics='" + "Тент"
                + ", carDriver='" + "Петров Петр Петрович"
                + "}";
        Assert.assertTrue(expected.equals(car.toString()));

    }

}
