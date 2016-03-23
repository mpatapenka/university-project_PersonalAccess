package org.diploma.personalaccess.web;

import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.entity.UserIndex;
import org.diploma.personalaccess.holder.PeriodHolder;
import org.diploma.personalaccess.service.UserIndexService;
import org.diploma.personalaccess.service.UserService;
import org.diploma.personalaccess.util.DateUtils;
import org.diploma.personalaccess.util.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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


    /**
     * User service bean
     */
    @Autowired
    private UserService userService;

    /**
     * User index service bean
     */
    @Autowired
    private UserIndexService userIndexService;

    /**
     * Period holder bean
     */
    @Autowired
    private PeriodHolder periodHolder;


    /**
     * Get user object by 'Principal' parameter (username)
     *
     * @param principal Principal object
     * @return User object
     */
    private User getUserBySecurityInfo(Principal principal) {
        String username = principal.getName();
        return userService.getUserByUsername(username);
    }


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfilePage(Model model, Principal principal) {
        User user = getUserBySecurityInfo(principal);

        model.addAttribute("uForm", user.getForm());
        model.addAttribute("subs", userService.getSubordinates(user));

        return "profile";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashboardPage(Model model, Principal principal,
                                   @RequestParam(required = false) Long periodId,
                                   @RequestParam(required = false) Integer year) {
        Period currentPeriod = periodHolder.getCurrentPeriod();
        boolean isEdit = (periodId == null && year == null)
                || (periodId != null && periodId == periodHolder.getIdOfPeriod(currentPeriod)
                && year != null && year == DateUtils.currentYear());

        User user = getUserBySecurityInfo(principal);
        Period period = isEdit || periodId == null ? currentPeriod : periodHolder.getPeriodById(periodId);
        int yearValue = isEdit || year == null ? DateUtils.currentYear() : year;
        List<UserIndex> userIndexes = userIndexService.getAllUserIndexesBySpecifiedPeriod(user, period,
                yearValue, currentPeriod, DateUtils.currentYear());

        model.addAttribute("period", period);
        model.addAttribute("periodNameCode", periodHolder.getPeriodsNameCode());
        model.addAttribute("periods", periodHolder.getAllPeriods());
        model.addAttribute("availYears", periodHolder.getAvailableYears());
        model.addAttribute("userIndexes", userIndexes);
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("selectedPeriodId", periodId);
        model.addAttribute("selectedYear", year);

        return "dashboard";
    }

    @ResponseBody
    @RequestMapping(value = "/dashboard/estimates", method = RequestMethod.POST)
    public String setupSelfEstimates(@RequestBody String data, Principal principal) {
        User user = getUserBySecurityInfo(principal);
        Type listType = new TypeToken<List<UserIndex>>() { }.getType();
        List<UserIndex> userIndexes = JsonParser.convertJsonStringToObject(data, listType);

        try {
            userIndexService.setupSelfEstimatesOfUser(userIndexes, user);
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
    public String getSubordinatePage(Model model, Principal principal,
                                     @RequestParam(required = false) Long periodId,
                                     @RequestParam(required = false) Integer year,
                                     @RequestParam(required = false) Long subId) {
        Period currentPeriod = periodHolder.getCurrentPeriod();
        boolean isEdit = (periodId != null && periodId == periodHolder.getIdOfPeriod(currentPeriod)
                && year != null && year == DateUtils.currentYear());

        User user = getUserBySecurityInfo(principal);
        Period period = isEdit || periodId == null ? currentPeriod : periodHolder.getPeriodById(periodId);
        int yearValue = isEdit || year == null ? DateUtils.currentYear() : year;
        List<UserIndex> userIndexes = null;

        if (subId != null) {
            User sub = userService.getUserById(subId);
            userIndexes = userIndexService.getAllUserIndexesForLeadBySpecifiedPeriod(sub,
                    period, yearValue);
        }

        model.addAttribute("userIndexes", userIndexes);
        model.addAttribute("subs", userService.getSubordinates(user));
        model.addAttribute("periods", periodHolder.getAllPeriods());
        model.addAttribute("periodNameCode", periodHolder.getPeriodsNameCode());
        model.addAttribute("availYears", periodHolder.getAvailableYears());
        model.addAttribute("selectedPeriodId", periodId == null ? periodHolder.getIdOfPeriod(currentPeriod) : periodId);
        model.addAttribute("selectedYear", year == null ? yearValue : year);
        model.addAttribute("selectedSubId", subId);
        model.addAttribute("isEdit", isEdit);

        return "subordinate";
    }

    @ResponseBody
    @RequestMapping(value = "/subs/estimates", method = RequestMethod.POST)
    public String setupLeadEstimates(@RequestBody String data, Principal principal) {
        User user = getUserBySecurityInfo(principal);
        Type mapType = new TypeToken<Map<Long, List<UserIndex>>>() { }.getType();
        Map<Long, List<UserIndex>> infoBundle = JsonParser.convertJsonStringToObject(data, mapType);

        Optional<Long> firstKeyOpt = infoBundle.keySet().stream().findFirst();
        long subId = firstKeyOpt.isPresent() ? firstKeyOpt.get() : -1;
        List<UserIndex> userIndexes = infoBundle.get(subId);
        User sub = userService.getUserById(subId);

        try {
            userIndexService.setupLeadEstimatesOfUser(userIndexes, user, sub);
            log.debug("'" + userIndexes.size() + "' user index lead estimates was updated.");
            /* Empty string it's valid answer from server, when it parse client application */
            return "";
        } catch (IllegalArgumentException e) {
            log.error("Error while processing estimates of lead '" + user.getUsername() + "'.", e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String getReportPage(Model model, Principal principal) {
        return "report";
    }

}
