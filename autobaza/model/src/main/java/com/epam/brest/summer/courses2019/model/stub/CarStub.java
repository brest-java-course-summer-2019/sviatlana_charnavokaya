package com.epam.brest.summer.courses2019.model.stub;

/**
 * POJO Car for model.
 */
public class CarStub {

    /**
     * Car Id.
     */
    private Integer carId;

    /**
     * Car Model.
     */
    private String carModel;

    /**
     * Car Number.
     */
    private String carNumber;

    /**
     * Car Load Capacity.
     */
    private Integer loadCapacity;

    /**
     * Car Сharacteristics.
     */
    private String carCharacteristics;

    /**
     * Car Driver.
     */
    private String carDriver;

    private Integer numberOfTrips;

    private  Integer totalDistance;


    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setLoadCapacity(Integer loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public void setCarCharacteristics(String carCharacteristics) {
        this.carCharacteristics = carCharacteristics;
    }

    public void setCarDriver(String carDriver) {
        this.carDriver = carDriver;
    }

    public Integer getCarId() {
        return carId;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public Integer getLoadCapacity() {
        return loadCapacity;
    }

    public String getCarCharacteristics() {
        return carCharacteristics;
    }

    public String getCarDriver() {
        return carDriver;
    }

    public Integer getNumberOfTrips() {
        return numberOfTrips;
    }

    public void setNumberOfTrips(Integer numberOfTrips) {
        this.numberOfTrips = numberOfTrips;
    }

    public Integer getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Integer totalDistance) {
        this.totalDistance = totalDistance;
    }

    @Override
    public String toString() {
        return "Car {"
                + "carId=" + carId
                + ", carModel='" + carModel
                + ", carNumber='" + carNumber
                + ", loadCapacity=" + loadCapacity
                + ", carCharacteristics='" + carCharacteristics
                + ", carDriver='" + carDriver
                + ", numberOfTrips='" + numberOfTrips
                + ", totalDistance='" + totalDistance
                + "}";
    }
}
