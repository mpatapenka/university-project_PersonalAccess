package org.diploma.personalaccess.web.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.diploma.personalaccess.web.WebConstants.Dir;
import static org.diploma.personalaccess.web.WebConstants.Page;

/**
 * Report controller, forming pages with reports
 *
 * @author Maksim Patapenka
 */
@PreAuthorize("isAuthenticated()")
@Controller
@RequestMapping("/report")
public class ReportController {

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String getReportEmployeesPage(Model model) {
        return Dir.REPORT + Page.REPORT_EMPLOYEES;
    }

    @RequestMapping(value = "/indexes", method = RequestMethod.GET)
    public String getReportIndexesPage(Model model) {
        return Dir.REPORT + Page.REPORT_INDEXES;
    }

    @RequestMapping(value = "/ranges", method = RequestMethod.GET)
    public String getReportRangesPage(Model model) {
        return Dir.REPORT + Page.REPORT_RANGES;
    }

}
