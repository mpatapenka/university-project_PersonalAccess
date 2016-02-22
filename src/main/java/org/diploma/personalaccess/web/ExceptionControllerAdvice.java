package org.diploma.personalaccess.web;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger log = Logger.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.warn("User trying get access to not exist page.", e);

        return "404";
    }

}
