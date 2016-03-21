package org.diploma.personalaccess.web;

import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.entity.UserIndex;
import org.diploma.personalaccess.holder.PeriodHolder;
import org.diploma.personalaccess.service.UserIndexService;
import org.diploma.personalaccess.util.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller which handle requests only from /user**
 *
 * @author Maksim Patapenka
 */
@PreAuthorize("isAuthenticated()")
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * Logger Log4j
     */
    private static final Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserIndexService userIndexService;

    @Autowired
    private PeriodHolder periodHolder;



    private User getUserBySecurityInfo(Principal principal) {
        String username = principal.getName();
        return (User) userDetailsService.loadUserByUsername(username);
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
        Period period = periodHolder.getCurrentPeriod();
        List<UserIndex> userIndexes = userIndexService.getAllUserIndexesBySpecifiedPeriod(user.getId(),
                period.getCurrentStartDate(), period.getCurrentEndDate());

        model.addAttribute("period", period);
        model.addAttribute("userIndexes", userIndexes);

        return "dashboard";
    }

    @ResponseBody
    @RequestMapping(value = "/dashboard/estimates", method = RequestMethod.POST)
    public String publishAllIndexes(@RequestBody String data, Principal principal) {
        User user = getUserBySecurityInfo(principal);
        Type listType = new TypeToken<List<UserIndex>>() { }.getType();
        List<UserIndex> userIndexes = JsonParser.convertJsonStringToObject(data, listType);

        try {
            userIndexService.setUpAllEstimates(userIndexes, user);
            log.debug("'" + userIndexes.size() + "' user index self estimates was updated.");
            /* Empty string it's valid answer from server, when it parse client application */
            return "";
        } catch (IllegalArgumentException e) {
            log.error("Error while processing estimates of user '" + user.getUsername() + "'.", e);
            return e.getMessage();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/dashboard/additional", method = RequestMethod.POST)
    public String uploadAdditionalUserIndexInfo(long id, String description,
                                                @RequestParam(required = false) MultipartFile document) {
        userIndexService.uploadAdditionalInfo(id, description, document);
        return "";
    }

    @RequestMapping(value = "/dashboard/download", method = RequestMethod.GET, params = {"id"})
    public void downloadAdditionalUserIndexInfo(@RequestParam int id, HttpServletResponse response) {
        userIndexService.downloadAdditionalInfo(id, response);
    }

    @RequestMapping(value = "/subs", method = RequestMethod.GET)
    public String getSubordinatePage(Model model, Principal principal) {
        User user = getUserBySecurityInfo(principal);

        model.addAttribute("subs", user.getSubs());
        model.addAttribute("periods", periodHolder.getAllPeriods());
        model.addAttribute("periodNameCode", periodHolder.getPeriodsNameCode());
        model.addAttribute("availYears", periodHolder.getAvailableYears());

        return "subordinate";
    }

    @ResponseBody
    @RequestMapping(value = "/subs", method = RequestMethod.GET, params = "search",
            produces = "text/plain; charset=utf-8")
    public String getSubordinateIndexes(long userId, long periodNum, int year) {
        Period period = periodHolder.getPeriodById(periodNum);
        List<UserIndex> subIndexes = userIndexService.getAllUserIndexesBySpecifiedPeriod(userId,
                period.getStartDateForYear(year), period.getEndDateForYear(year));
        boolean isSubmitted = userIndexService.isLeadSubmitAllEstimatesForUser(userId,
                period.getStartDateForYear(year), period.getEndDateForYear(year));

        Map<String, Object> entries = new HashMap<>();
        entries.put("isSubmitted", isSubmitted);
        entries.put("subIndexes", subIndexes);

        return JsonParser.convertObjectToJsonString(entries);
    }

    @ResponseBody
    @RequestMapping(value = "/subs/publish", method = RequestMethod.POST)
    public String publishSubordinateIndexes(@RequestBody String data, Principal principal) {
        User user = getUserBySecurityInfo(principal);
        Type mapType = new TypeToken<Map<Long, Integer>>() { }.getType();
        Map<Long, Integer> entries = JsonParser.convertJsonStringToObject(data, mapType);
        userIndexService.publishLeadEstimates(entries, user);

        return "success";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String getReportPage(Model model, Principal principal) {
        return "report";
    }

}
