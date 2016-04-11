package org.diploma.personalaccess.web.error;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

import static org.diploma.personalaccess.web.WebConstants.*;

/**
 * Controller which handle errors and show it on page
 *
 * @author Maksim Patapenka
 */
@Controller
@RequestMapping("/error")
public class ExceptionController {

    /**
     * Logger Log4j
     */
    private static final Logger log = Logger.getLogger(ExceptionController.class);


    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String handleAccessDenied(Principal user) {
        log.warn("User '" + (user != null ? "login = " + user.getName() : User.ANONYMOUS)
                + "' trying to get access to secured page.");
        return Dir.ERROR + Page.PAGE_403;
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String handleResourceNotFound(Principal user) {
        log.warn("User '" + (user != null ? "login = " + user.getName() : User.ANONYMOUS)
                + "' trying to get access to not exists page.");
        return Dir.ERROR + Page.PAGE_404;
    }

}
