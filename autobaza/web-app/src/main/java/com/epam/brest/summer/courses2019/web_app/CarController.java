package com.epam.brest.summer.courses2019.web_app;

import com.epam.brest.summer.courses2019.model.Car;
import com.epam.brest.summer.courses2019.service.CarService;
import com.epam.brest.summer.courses2019.web_app.validators.CarValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Car controller.
 */
@Controller
public class CarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @Autowired
    CarValidator carValidator;

    /**
     * Goto cars list page.
     *
     * @param model model
     * @return view name
     */
    @GetMapping(value = "/cars")
    public final String cars(Model model) {
        LOGGER.debug("findAll({})", model);
        model.addAttribute("cars", carService.findAll());
        System.out.println("11111111111111111111111111111111111findAll({})" + model);

        return "cars";
    }

    /**
     * Goto add car page.

     * @return view name
     */
    @GetMapping(value = "/car")
    public final String gotoAddCarPage(Model model) {

        LOGGER.debug("gotoAddCarPage({})", model);
        Car car = new Car();
        model.addAttribute("car", car);
        model.addAttribute("isNew", true);

        return "car";
    }

    /**
     * Goto edit car page.
     *
     * @param model model
     * @return view name
     */
    @GetMapping(value = "/car/{id}")
    public final String gotoEditCarPage(@PathVariable Integer id, Model model) {

        LOGGER.debug("gotoEditCarPage({},{})", id, model);
        Car car = carService.findById(id);
        model.addAttribute("car", car);

        return "car";
    }

    /**
     * Delete car.
     *
     * @return view name
     */
    @GetMapping(value = "/car/{id}/delete")
    public final String deleteCarById(@PathVariable Integer id, Model model) {
        LOGGER.debug("delete({},{})", id, model);
        carService.delete(id);
        return "redirect:/cars";
    }

    /**
     * Update car into persistence storage.
     *
     * @return view name
     */
    @PostMapping(value = "/car/{id}")
    public String updateCar(@Valid Car car,
                                   BindingResult result) {

        LOGGER.debug("updateCar({}, {})", car, result);
        carValidator.validate(car, result);
        if (result.hasErrors()) {
            return "car";
        } else {
            this.carService.update(car);
            return "redirect:/cars";
        }
    }

    /**
     * Persist new car into persistence storage.
     *
     * @param car new car with filled data.
     * @param result     binding result.
     * @return view name
     */
    @PostMapping(value = "/car")
    public String addCar(@Valid Car car,
                                BindingResult result) {

        LOGGER.debug("addCar({}, {})", car, result);
        carValidator.validate(car, result);
        if (result.hasErrors()) {
            return "car";
        } else {
            this.carService.add(car);
            return "redirect:/cars";
        }
    }
}
