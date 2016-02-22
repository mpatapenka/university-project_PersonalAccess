package org.diploma.personalaccess.web;

import org.diploma.personalaccess.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDetailsService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfilePage(Model model, Principal principal) {
        String username = principal.getName();
        User user = (User) userService.loadUserByUsername(username);

        String userFullName = user.getForm().getFirstName() + " " + user.getForm().getLastName();
        String positionName = user.getForm().getPosition().getName();
        String userFulledName = user.getForm().getLastName() + " " + user.getForm().getFirstName() + " "
                + user.getForm().getMiddleName();
        String unitName = user.getForm().getUnit().getName();
        String facultyFullName = user.getForm().getFaculty().getFullName();
        String facultyShortName = user.getForm().getFaculty().getShortName();
        Set<User> subordinates = user.getSubs();

        model.addAttribute("userFullName", userFullName);
        model.addAttribute("positionName", positionName);
        model.addAttribute("userFulledName", userFulledName);
        model.addAttribute("unitName", unitName);
        model.addAttribute("facultyFullName", facultyFullName);
        model.addAttribute("facultyShortName", facultyShortName);
        model.addAttribute("subs", subordinates);

        return "profile";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashboardPage() {
        return "dashboard";
    }

}
