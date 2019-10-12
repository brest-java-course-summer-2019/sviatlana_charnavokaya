package com.epam.brest.summer.courses2019.web_app;

import com.epam.brest.summer.courses2019.model.Trip;
import com.epam.brest.summer.courses2019.service.CarService;
import com.epam.brest.summer.courses2019.service.TripService;
import com.epam.brest.summer.courses2019.web_app.validators.TripValidator;
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
 * Trip controller.
 */
@Controller
public class TripController {


    private static final Logger LOGGER = LoggerFactory.getLogger(TripController.class);

    @Autowired
    private TripService tripService;

    @Autowired
    private CarService carService;

    @Autowired
    TripValidator tripValidator;

    /**
     * Goto trips page.
     *
     * @param model model
     * @return view name
     */
    @GetMapping(value = "/trips")
    public final String trips(Model model) {
        LOGGER.debug("findAll({})", model);
        model.addAttribute("trips", tripService.findAll());
        model.addAttribute("tripStatuses", tripService.findAllTripStatuses());
        model.addAttribute("cars", carService.findAll());
        return "trips";
    }

    /**
     * Goto add trip page.

     * @return view name
     */
    @GetMapping(value = "/trip")
    public final String gotoAddTripPage(Model model) {

        LOGGER.debug("gotoAddTripPage({})", model);
        Trip trip = new Trip();
        model.addAttribute("trip", trip);
        model.addAttribute("isNew", true);
        model.addAttribute("tripStatuses", tripService.findAllTripStatuses());
        model.addAttribute("cars", carService.findAll());

        return "trip";
    }

    /**
     * Goto edit trip page.
     *
     * @param model model
     * @return view name
     */
    @GetMapping(value = "/trip/{id}")
    public final String gotoEditTripPage(@PathVariable Integer id, Model model) {

        LOGGER.debug("gotoEditTripPage({},{})", id, model);
        Trip trip = tripService.findById(id);
        model.addAttribute("trip", trip);
        model.addAttribute("tripStatuses", tripService.findAllTripStatuses());
        model.addAttribute("cars", carService.findAll());

        return "trip";
    }

    /**
     * Delete trip.
     *
     * @return view name
     */
    @GetMapping(value = "/trip/{id}/delete")
    public final String deleteTripById(@PathVariable Integer id, Model model) {
        LOGGER.debug("delete({},{})", id, model);
        tripService.delete(id);
        return "redirect:/trips";
    }

    /**
     * Update trip into persistence storage.
     *
     * @return view name
     */
    @PostMapping(value = "/trip/{id}")
    public String updateTrip(@Valid Trip trip,
                            BindingResult result, Model model) {

        LOGGER.debug("updateTrip({}, {})", trip, result);
        tripValidator.validate(trip, result);
        if (result.hasErrors()) {
            model.addAttribute("tripStatuses", tripService.findAllTripStatuses());
            model.addAttribute("cars", carService.findAll());
            return "trip";
        } else {
            this.tripService.update(trip);
            return "redirect:/trips";
        }
    }

    /**
     * Persist new trip into persistence storage.
     *
     * @param trip new trip with filled data.
     * @param result     binding result.
     * @return view name
     */
    @PostMapping(value = "/trip")
    public String addTrip(@Valid Trip trip,
                         BindingResult result, Model model) {

        LOGGER.debug("addTrip({}, {})", trip, result);
        tripValidator.validate(trip, result);
        if (result.hasErrors()) {
            model.addAttribute("tripStatuses", tripService.findAllTripStatuses());
            model.addAttribute("cars", carService.findAll());
            return "trip";
        } else {
            this.tripService.add(trip);
            return "redirect:/trips";
        }
    }
}
