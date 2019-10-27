package com.epam.brest.summer.courses2019.web_app.consumers;

import com.epam.brest.summer.courses2019.model.Car;
import com.epam.brest.summer.courses2019.model.stub.CarStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarRestConsumerTest {

    private static final String CAR_MODEL = "carModel";
    private static final String CAR_NUMBER = "carNumber";
    private static final String CAR_DRIVER = "carDriver";
    private static final String CAR_CHARACTERISTICS= "carCharacteristics";
    private static final Integer CAR_ID_1 = 1;
    private static final Integer CAR_ID_2 = 2;
    private static final Integer LOAD_CAPACITY_VALUE = 1000;
    private static final Integer NUMBER_OF_TRIPS_VALUE = 5;
    private static final Integer TOTAL_DISTANCE_VALUE = 2323;


    private RestTemplate restTemplate;

    private String url = "/cars";

    private CarRestConsumer carRestConsumer;

    @BeforeEach
    void setUp() {
        restTemplate = Mockito.mock(RestTemplate.class);
        carRestConsumer = new CarRestConsumer(url, restTemplate);
    }


    @Test
    public void findAll() throws Exception {
        List<Car> cars = new ArrayList<>();
        cars.add(createCar(CAR_ID_1));
        cars.add(createCar(CAR_ID_2));

        Mockito.when(restTemplate.getForEntity(url + "/", List.class))
                .thenReturn(new ResponseEntity<>(cars, HttpStatus.OK));

        List<Car> expected = carRestConsumer.findAll();

        assertNotNull(expected);
        assertEquals(expected, cars);

        Mockito.verify(restTemplate, Mockito.times(1)).getForEntity(url + "/", List.class);
    }

    @Test
    public void findAllWithDistanceAndTrips() throws Exception {
        List<CarStub> stubs = new ArrayList<>();
        stubs.add(createCarStub(CAR_ID_1));
        stubs.add(createCarStub(CAR_ID_2));

        Mockito.when(restTemplate.getForEntity(url + "/all/", List.class))
                .thenReturn(new ResponseEntity<>(stubs, HttpStatus.OK));

        List<CarStub> expected = carRestConsumer.findAllWithDistanceAndTrips();

        assertNotNull(expected);
        assertEquals(expected, stubs);

        Mockito.verify(restTemplate, Mockito.times(1)).getForEntity(url + "/all/", List.class);
    }

    @Test
    public void findById() throws Exception {

        Car expected = createCar(CAR_ID_1);

        Mockito.when(restTemplate.getForEntity(url + "/" + CAR_ID_1, Car.class))
                .thenReturn(new ResponseEntity<>(createCar(CAR_ID_1), HttpStatus.OK));

        Car car = carRestConsumer.findById(CAR_ID_1);

        assertEquals(expected.getCarId(), car.getCarId());
        assertEquals(expected.getCarNumber(), car.getCarNumber());
        assertEquals(expected.getCarCharacteristics(), car.getCarCharacteristics());
        assertEquals(expected.getLoadCapacity(), car.getLoadCapacity());


        Mockito.verify(restTemplate, Mockito.times(1)).getForEntity(url + "/" + CAR_ID_1, Car.class);

    }


    @Test
    void add() {
        Car car = createCar(CAR_ID_1);

        Mockito.when(restTemplate.postForEntity(url, car, Car.class))
                .thenReturn(new ResponseEntity<>(car, HttpStatus.OK));

        Car expected = carRestConsumer.add(car);

        assertEquals(expected.getCarId(), car.getCarId());
        assertEquals(expected.getCarNumber(), car.getCarNumber());
        assertEquals(expected.getCarCharacteristics(), car.getCarCharacteristics());
        assertEquals(expected.getLoadCapacity(), car.getLoadCapacity());

        Mockito.verify(restTemplate, Mockito.times(1)).postForEntity(url, car, Car.class);
    }


    @Test
    public void update() throws Exception {
        Car car = createCar(CAR_ID_1);

        carRestConsumer.update(car);
        Mockito.verify(restTemplate, Mockito.times(1)).put(url, car);

    }


    @Test
    public void delete() throws Exception {

        carRestConsumer.delete(CAR_ID_1);

        Mockito.verify(restTemplate, Mockito.times(1)).delete(url +"/" + CAR_ID_1);
    }


    private Car createCar(int index) {
        Car car = new Car();
        car.setCarId(index);
        car.setCarModel(CAR_MODEL + index);
        car.setCarNumber(CAR_NUMBER + index);
        car.setCarDriver(CAR_DRIVER + index);
        car.setCarCharacteristics(CAR_CHARACTERISTICS + index);
        car.setLoadCapacity(LOAD_CAPACITY_VALUE + index);

        return car;
    }

    private CarStub createCarStub(int index) {
        CarStub car = new CarStub();
        car.setCarId(index);
        car.setCarModel(CAR_MODEL + index);
        car.setCarNumber(CAR_NUMBER + index);
        car.setCarDriver(CAR_DRIVER + index);
        car.setCarCharacteristics(CAR_CHARACTERISTICS + index);
        car.setLoadCapacity(LOAD_CAPACITY_VALUE + index);
        car.setNumberOfTrips(NUMBER_OF_TRIPS_VALUE + index);
        car.setTotalDistance(TOTAL_DISTANCE_VALUE + index);

        return car;
    }

}
