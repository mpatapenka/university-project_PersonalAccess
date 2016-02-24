package org.diploma.personalaccess.web;

import org.diploma.personalaccess.entity.Index;
import org.diploma.personalaccess.entity.Position;
import org.diploma.personalaccess.service.IndexService;
import org.diploma.personalaccess.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private PositionService positionService;



    @ModelAttribute("positions")
    public List<Position> loadAllowedPositions() {
        return positionService.findAll();
    }



    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashboardPage() {
        return "admin";
    }

    @ResponseBody
    @RequestMapping(value = "/dashboard/new", method = RequestMethod.POST)
    public String addNewIndex(/*BindingResult bindingResult, */Index index) {
        //if (!bindingResult.hasErrors()) {
            indexService.saveOrUpdateIndex(index);
            return "";
        //}
        //return "error";
    }

}
