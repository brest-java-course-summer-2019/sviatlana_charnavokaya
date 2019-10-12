package com.epam.brest.summer.courses2019.web_app.validators;

import com.epam.brest.summer.courses2019.model.Trip;
import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TripValidator implements Validator {

    public static final int TRIP_DISTANCE_MAX_SIZE = 10000;


    @Override
    public boolean supports(Class<?> clazz) {
        return Trip.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "distance", "distance.empty");
        Trip trip = (Trip) target;

        if (trip.getDistance() > TRIP_DISTANCE_MAX_SIZE) {
            errors.rejectValue("distance", "distance.maxSize10000");
        }
        if (trip.getDistance() < 0) {
            errors.rejectValue("distance", "distance.negative");
        }


    }
}
