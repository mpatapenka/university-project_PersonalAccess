package org.diploma.personalaccess.web;

import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.service.holder.PeriodHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Controller which handle requests only from /user**
 *
 * @author Maksim Patapenka
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private PeriodHolder periodHolder;



    private User getUserBySecurityInfo(Principal principal) {
        String username = principal.getName();
        return (User) userService.loadUserByUsername(username);
    }



    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfilePage(Model model, Principal principal) {
        User user = getUserBySecurityInfo(principal);

        model.addAttribute("uForm", user.getForm());
        model.addAttribute("subs", user.getSubs());

        return "profile";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashboardPage(Model model, Principal principal) {
        User user = getUserBySecurityInfo(principal);

        model.addAttribute("availIndexes", user.getForm().getPosition().getAvailableIndexes());
        model.addAttribute("period", periodHolder.getCurrentPeriod());

        return "dashboard";
    }

}
