package com.epam.brest.summer.courses2019.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Trip controller.
 */
@Controller
public class TripController {

    /**
     * Goto trips page.
     *
     * @return view name
     */
    @GetMapping(value = "/trips")
    public final String employees() {
        return "trips";
    }

    /**
     * Goto trip page.
     *
     * @return view name
     */
    @GetMapping(value = "/trip")
    public final String employee() {
        return "trip";
    }
}
