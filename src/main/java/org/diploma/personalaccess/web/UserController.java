package org.diploma.personalaccess.web;

import com.google.gson.reflect.TypeToken;
import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.entity.Index;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Controller which handle requests only from /user**
 *
 * @author Maksim Patapenka
 */
@PreAuthorize("isAuthenticated()")
@Controller
@RequestMapping("/user")
public class UserController {

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
        boolean isFilled = userIndexService.isUserIndexesAvailableForPeriod(user, period);

        List<UserIndex> userIndexes = isFilled
                ? userIndexService.getAllUserIndexesBySpecifiedPeriod(user,
                period.getCurrentStartDate(), period.getCurrentEndDate())
                : new ArrayList<>();
        Set<Index> availIndexes = !isFilled
                ? user.getForm().getPosition().getAvailableIndexes()
                : new HashSet<>();

        model.addAttribute("isFilled", isFilled);
        model.addAttribute("period", period);
        model.addAttribute("userIndexes", userIndexes);
        model.addAttribute("availIndexes", availIndexes);

        return "dashboard";
    }

    @ResponseBody
    @RequestMapping(value = "/dashboard/publish", method = RequestMethod.POST)
    public String publishAllIndexes(@RequestBody String data, Principal principal) {
        User user = getUserBySecurityInfo(principal);
        Type listType = new TypeToken<List<UserIndex>>() { }.getType();
        List<UserIndex> userIndexes = JsonParser.convertJsonStringToObject(data, listType);

        userIndexService.publishAllUserIndexes(userIndexes, user);

        return "success";
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
    @RequestMapping(value = "/subs", method = RequestMethod.GET, params = "search")
    public String getSubordinateIndexes(long userId, long periodNum, int year) {
        Period period = periodHolder.getPeriodById(periodNum);
        List<UserIndex> subIndexes = userIndexService.getAllUserIndexesBySpecifiedPeriod(userId,
                period.getStartDateForYear(year), period.getEndDateForYear(year));

        return JsonParser.convertObjectToJsonString(subIndexes);
    }

}
