package com.epam.brest.summer.courses2019.web_app.validators;

import com.epam.brest.summer.courses2019.model.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;

public class TripValidatorTest {

    private Trip trip;

    private TripValidator tripValidator = new TripValidator();
    private BindingResult result;


    @BeforeEach
    void setup() {
        trip = Mockito.mock(Trip.class);
        Mockito.when(trip.getDistance()).thenReturn(500);
        result = new BeanPropertyBindingResult(trip, "trip");

    }

    @Test
    void checkValidTrip() {
        tripValidator.validate(trip, result);
        assertFalse(result.hasErrors());
    }

    @Test
    void rejectIfNullDistance() {
        Mockito.when(trip.getDistance()).thenReturn(null);
        tripValidator.validate(trip, result);
        assertTrue(result.hasErrors());
    }

    @Test
    void rejectNegativeDistance() {
        Mockito.when(trip.getDistance()).thenReturn(-5);
        tripValidator.validate(trip, result);
        assertTrue(result.hasErrors());
    }

    @Test
    void rejectLargeDistance() {
        Mockito.when(trip.getDistance()).thenReturn(100000);
        tripValidator.validate(trip, result);
        assertTrue(result.hasErrors());
    }


}
