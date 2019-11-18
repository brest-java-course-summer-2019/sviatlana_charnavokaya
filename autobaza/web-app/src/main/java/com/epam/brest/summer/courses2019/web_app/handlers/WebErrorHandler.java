package com.epam.brest.summer.courses2019.web_app.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class WebErrorHandler {

   private static final Logger LOGGER = LoggerFactory.getLogger(WebErrorHandler.class);

    /**
     * method handleServerException handle app exceptions
     *
     * @param model model
     * @param ex    Exception
     * @return template name
     */
    @ExceptionHandler(HttpServerErrorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleServerException(HttpServerErrorException ex, Model model) {
        model.addAttribute("Title", "No such page");
        model.addAttribute("Text", ex.toString());
        return "exception";
    }

    /**
     * method handleClientException handle app exceptions
     *
     * @param model model
     * @param ex    Exception
     * @return template name
     */
   @ExceptionHandler(HttpClientErrorException.class)
//   @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleClientException(HttpClientErrorException ex, Model model) {
        LOGGER.debug("handleAnyException({}, {})", ex, model);
        model.addAttribute("Title", "Action not possible");
        model.addAttribute("Text", ex.toString());
        return "exception";
    }




}
