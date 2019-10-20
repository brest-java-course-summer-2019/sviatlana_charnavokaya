package com.epam.brest.summer.courses2019.web_app.validators;

import com.epam.brest.summer.courses2019.model.Car;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CarValidator implements Validator {

    public static final int CAR_MODEL_MAX_SIZE = 15;
    public static final int CAR_NUMBER_MAX_SIZE = 12;
    public static final int CAR_CHARACTERISTICS_MAX_SIZE = 20;
    public static final int CAR_DRIVER_MAX_SIZE = 18;
    public static final int CAR_LOAD_CAPACITY_MAX_SIZE = 50000;

    @Override
    public boolean supports(Class<?> clazz) {
        return Car.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "carModel", "carModel.empty");
        ValidationUtils.rejectIfEmpty(errors, "carNumber", "carNumber.empty");
        ValidationUtils.rejectIfEmpty(errors, "loadCapacity", "loadCapacity.empty");


        Car car = (Car) target;

        if (StringUtils.hasLength(car.getCarModel())
                && car.getCarModel().length() > CAR_MODEL_MAX_SIZE) {
            errors.rejectValue("carModel", "carModel.maxSize15");
        }

        if (StringUtils.hasLength(car.getCarModel())
                && car.getCarNumber().length() > CAR_NUMBER_MAX_SIZE) {
            errors.rejectValue("carNumber", "carNumber.maxSize12");
        }

        if (StringUtils.hasLength(car.getCarCharacteristics())
                && car.getCarCharacteristics().length() > CAR_CHARACTERISTICS_MAX_SIZE) {
            errors.rejectValue("carCharacteristics", "carCharacteristics.maxSize20");
        }
        if (StringUtils.hasLength(car.getCarDriver())
                && car.getCarDriver().length() > CAR_DRIVER_MAX_SIZE) {
            errors.rejectValue("carDriver", "carDriver.maxSize18");
        }

        if (!(car.getLoadCapacity() instanceof Integer)){
            errors.rejectValue("loadCapacity", "loadCapacity.typeInteger");
        }else if (car.getLoadCapacity() > CAR_LOAD_CAPACITY_MAX_SIZE) {
            errors.rejectValue("loadCapacity", "loadCapacity.maxSize50000");
        }else if (car.getLoadCapacity() < 0) {
            errors.rejectValue("loadCapacity", "loadCapacity.negative");
        }




    }
}
