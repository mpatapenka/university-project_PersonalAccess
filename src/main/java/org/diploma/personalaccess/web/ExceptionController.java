package org.diploma.personalaccess.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

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
        log.warn("User '" + (user != null ? "login = " + user.getName() : "anonymous") + "' trying get acces to secured page.");

        return "403";
    }

}
