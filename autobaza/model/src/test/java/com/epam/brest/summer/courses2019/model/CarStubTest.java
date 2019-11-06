package com.epam.brest.summer.courses2019.model;

import com.epam.brest.summer.courses2019.model.stub.CarStub;
import org.junit.Assert;
import org.junit.Test;

public class CarStubTest {


    private static final Integer CAR_ID = 15;
    private static final String CAR_MODEL = "Mersedes";
    private static final String CAR_NUMBER = "44-72 AA-1";
    private static final String CAR_DRIVER = "Петров Петр Петрович";
    private static final String CAR_CHARACTERISTICS= "Тент";
    private static final Integer LOAD_CAPACITY = 20;
    private static final Integer NUMBER_OF_TRIPS = 10;
    private static final Integer TOTAL_DISTANCE = 12345;

    CarStub carStub = new CarStub();

    @Test
    public void getCarId() {
        carStub.setCarId(CAR_ID);
        Assert.assertTrue(carStub.getCarId().equals(CAR_ID));
    }

    @Test
    public void getCarModel() {
        carStub.setCarModel(CAR_MODEL);
        Assert.assertTrue(carStub.getCarModel().equals(CAR_MODEL));
    }

    @Test
    public void getCarNunber() {
        carStub.setCarNumber(CAR_NUMBER);
        Assert.assertTrue(carStub.getCarNumber().equals(CAR_NUMBER));
    }

    @Test
    public void getLoadCapacity() {
        carStub.setLoadCapacity(LOAD_CAPACITY);
        Assert.assertTrue(carStub.getLoadCapacity().equals(LOAD_CAPACITY));
    }

    @Test
    public void getCarCharacteristics() {
        carStub.setCarCharacteristics(CAR_CHARACTERISTICS);
        Assert.assertTrue(carStub.getCarCharacteristics().equals(CAR_CHARACTERISTICS));
    }

    @Test
    public void getCarDriver() {
        carStub.setCarDriver(CAR_DRIVER);
        Assert.assertTrue(carStub.getCarDriver().equals(CAR_DRIVER));
    }
    
    @Test
    public void getNumberOfTrips(){
        carStub.setNumberOfTrips(NUMBER_OF_TRIPS);
        Assert.assertTrue(carStub.getNumberOfTrips().equals(NUMBER_OF_TRIPS));
    }

    @Test
    public void getTotalDistance(){
        carStub.setTotalDistance(TOTAL_DISTANCE);
        Assert.assertTrue(carStub.getTotalDistance().equals(TOTAL_DISTANCE));
    }

    @Test
    public void testToString() {
        carStub.setCarId(CAR_ID);
        carStub.setCarModel(CAR_MODEL);
        carStub.setCarNumber(CAR_NUMBER);
        carStub.setLoadCapacity(LOAD_CAPACITY);
        carStub.setCarCharacteristics(CAR_CHARACTERISTICS);
        carStub.setCarDriver(CAR_DRIVER);
        carStub.setNumberOfTrips(NUMBER_OF_TRIPS);
        carStub.setTotalDistance(TOTAL_DISTANCE);


        String expected = "Car {"
                + "carId=" + CAR_ID
                + ", carModel='" + CAR_MODEL
                + ", carNumber='" + CAR_NUMBER
                + ", loadCapacity=" + 20
                + ", carCharacteristics='" + CAR_CHARACTERISTICS
                + ", carDriver='" + CAR_DRIVER
                + ", numberOfTrips='" + NUMBER_OF_TRIPS
                + ", totalDistance='" + TOTAL_DISTANCE
                + "}";
        Assert.assertTrue(expected.equals(carStub.toString()));

    }
}
