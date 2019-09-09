package com.epam.brest.summer.courses2019.web_app.com.epam.brest.summer.courses2019.web_app.validators;

import com.epam.brest.summer.courses2019.model.Car;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CarValidator implements Validator {

    public static final int CAR_MODEL_MAX_SIZE = 15;

    @Override
    public boolean supports(Class<?> clazz) {
        return Car.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "carModel", "carModel.empty");
        Car car = (Car) target;

        if (StringUtils.hasLength(car.getCarModel())
                && car.getCarModel().length() > CAR_MODEL_MAX_SIZE) {
            errors.rejectValue("carModel", "carModel.maxSize15");
        }

    }
}
