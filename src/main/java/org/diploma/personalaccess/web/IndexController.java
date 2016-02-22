package org.diploma.personalaccess.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getStartPage(Principal principal) {
        return principal != null ? "redirect:/user/dashboard" : "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) String logout,
                               Model model) {
        model.addAttribute("isError", error != null);
        model.addAttribute("isLogout", logout != null);

        return "login";
    }

}
