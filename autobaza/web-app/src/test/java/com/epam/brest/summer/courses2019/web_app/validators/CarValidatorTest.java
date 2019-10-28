package com.epam.brest.summer.courses2019.web_app.validators;

import com.epam.brest.summer.courses2019.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.thymeleaf.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

public class CarValidatorTest {

    private static final String VALID_CAR_MODEL = "carModel";
    private static final String CAR_MODEL_SIZE16 = StringUtils.repeat("*", 16);
    private static final String VALID_CAR_NUMBER = "AA-4 55-66";
    private static final String CAR_NUMBER_SIZE13 = StringUtils.repeat("*", 13);
    private static final String VALID_CAR_DRIVER = "Ivanov";
    private static final String CAR_DRIVER_SIZE19 = StringUtils.repeat("*", 19);
    private static final String VALID_CAR_CHARACTERISTICS= "carCharacteristics";
    private static final String CAR_CHARACTERISTICS_SIZE21= StringUtils.repeat("*", 21);

    private static final Integer VALID_LOAD_CAPACITY = 1000;
    private static final Integer LOAD_CAPACITY_NEGATIVE = -5;
    private static final Integer LOAD_CAPACITY_50001 = 50001;


    private Car car;

    private CarValidator carValidator = new CarValidator();
    private BindingResult result;


    @BeforeEach
    void setup() {
        car = createValidCar();
        result = new BeanPropertyBindingResult(car, "car");

    }

    @Test
    void checkValidCar() {
        carValidator.validate(car, result);
        assertFalse(result.hasErrors());
    }


    @Test
    void rejectIfNullCarModel() {
        car.setCarModel(null);
        carValidator.validate(car, result);
        assertTrue(result.hasErrors());
        assertEquals(1, result.getErrorCount());
    }

    @Test
    void rejectIfNullCarNumber() {
        car.setCarNumber(null);
        carValidator.validate(car, result);
        assertTrue(result.hasErrors());
        assertEquals(1, result.getErrorCount());
    }

    @Test
    void rejectIfEmptyCarModel() {
        car.setCarModel("");
        carValidator.validate(car, result);
        assertTrue(result.hasErrors());
        assertEquals(1, result.getErrorCount());
    }

    @Test
    void rejectIfEmptyCarNumber() {
        car.setCarNumber("");
        carValidator.validate(car, result);
        assertTrue(result.hasErrors());
        assertEquals(1, result.getErrorCount());
    }


    @Test
    void rejectLargeCarModel() {
        car.setCarModel(CAR_MODEL_SIZE16);
        carValidator.validate(car, result);
        assertTrue(result.hasErrors());
    }

    @Test
    void rejectLargeCarNumber() {
        car.setCarNumber(CAR_NUMBER_SIZE13);
        carValidator.validate(car, result);
        assertTrue(result.hasErrors());
        assertEquals(1, result.getErrorCount());
    }

    @Test
    void rejectLargeCarCharacteristic() {
       car.setCarCharacteristics(CAR_CHARACTERISTICS_SIZE21);
        carValidator.validate(car, result);
        assertTrue(result.hasErrors());
        assertEquals(1, result.getErrorCount());
    }

    @Test
    void rejectLargeCarDriver() {
        car.setCarDriver(CAR_DRIVER_SIZE19);
        carValidator.validate(car, result);
        assertTrue(result.hasErrors());
    }

    @Test
    void rejectNegativeLoadCapacity() {
        car.setLoadCapacity(LOAD_CAPACITY_NEGATIVE);
        carValidator.validate(car, result);
        assertTrue(result.hasErrors());
        assertEquals(1, result.getErrorCount());
    }

    @Test
    void rejectLargeLoadCapacity() {
        car.setLoadCapacity(LOAD_CAPACITY_50001);
        carValidator.validate(car, result);
        assertTrue(result.hasErrors());
        assertEquals(1, result.getErrorCount());
    }


    private Car createValidCar() {
        Car car = new Car();
        car.setCarModel(VALID_CAR_MODEL);
        car.setCarNumber(VALID_CAR_NUMBER);
        car.setCarDriver(VALID_CAR_DRIVER);
        car.setCarCharacteristics(VALID_CAR_CHARACTERISTICS);
        car.setLoadCapacity(VALID_LOAD_CAPACITY);

        return car;
    }



}
