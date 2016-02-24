package org.diploma.personalaccess.web;

import org.diploma.personalaccess.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDetailsService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfilePage(Model model, Principal principal) {
        String username = principal.getName();
        User user = (User) userService.loadUserByUsername(username);

        model.addAttribute("uForm", user.getForm());
        model.addAttribute("subs", user.getSubs());

        return "profile";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashboardPage() {
        return "dashboard";
    }

}
