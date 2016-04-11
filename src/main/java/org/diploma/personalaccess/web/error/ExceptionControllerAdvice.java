package org.diploma.personalaccess.web.error;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.diploma.personalaccess.web.WebConstants.REDIRECT;

/**
 * Controller advice which handle exceptions
 *
 * @author Maksim Patapenka
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * Logger Log4j
     */
    private static final Logger log = Logger.getLogger(ExceptionControllerAdvice.class);


    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.debug("User trying to get access to not exist page.", e);
        return REDIRECT + "/error/404";
    }

}
