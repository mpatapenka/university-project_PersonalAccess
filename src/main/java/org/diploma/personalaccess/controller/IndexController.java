package org.diploma.personalaccess.controller;

import org.diploma.personalaccess.service.PersonalAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @Autowired
    private PersonalAccessService personalAccessService;

    @RequestMapping(value = {"/", "/dashboard"}, method = RequestMethod.GET)
    public String getDashboardPage() {
        return "dashboard";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String getErrorPage() {
        return "error";
    }

}
