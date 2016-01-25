package org.diploma.personalaccess.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pa")
public class IndexController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public String getTest() {
        return "Hello, World!";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        return "hello";
    }

}
