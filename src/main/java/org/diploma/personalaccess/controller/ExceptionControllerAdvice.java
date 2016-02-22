package org.diploma.personalaccess.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger log = Logger.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.warn("User trying get access to secured page.", e);

        ModelAndView modelAndView = new ModelAndView("404");
        modelAndView.addObject("exception", e);

        return modelAndView;
    }

}
