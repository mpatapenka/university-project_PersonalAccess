package org.diploma.personalaccess.web;

import org.diploma.personalaccess.entity.Index;
import org.diploma.personalaccess.service.IndexService;
import org.diploma.personalaccess.service.PositionService;
import org.diploma.personalaccess.util.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * That secured area only fo admins
 *
 * @author Maksim Patapenka
 */
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private PositionService positionService;



    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashboardPage(Model model) {
        model.addAttribute("indexes", indexService.findAllIndexes());
        model.addAttribute("positions", positionService.findAll());
        return "admin";
    }

    @ResponseBody
    @RequestMapping(value = "/dashboard/save", method = RequestMethod.POST)
    public String saveIndex(@RequestBody String data) {
        Index index = JsonParser.convertJsonStringToObject(data, Index.class);
        indexService.saveOrUpdateIndex(index);
        return "Index '" + index.getName() + "' was saved.";
    }

    @ResponseBody
    @RequestMapping(value = "/dashboard/get", method = RequestMethod.GET, params = "id",
            produces = "text/plain; charset=utf-8")
    public String getIndex(long id) {
        Index index = indexService.findIndexById(id);
        return JsonParser.convertObjectToJsonString(index);
    }

    @ResponseBody
    @RequestMapping(value = "/dashboard/delete", method = RequestMethod.POST)
    public String deleteIndex(long id) {
        indexService.deleteIndex(id);
        return "Index with id='" + id + "' was deleted.";
    }

}
