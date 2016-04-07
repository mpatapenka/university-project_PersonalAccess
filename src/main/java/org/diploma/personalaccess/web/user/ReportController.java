package org.diploma.personalaccess.web.user;

import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.bean.Rate;
import org.diploma.personalaccess.holder.PeriodHolder;
import org.diploma.personalaccess.service.PositionService;
import org.diploma.personalaccess.service.RateService;
import org.diploma.personalaccess.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

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

    @Autowired
    private PositionService positionService;

    @Autowired
    private PeriodHolder periodHolder;

    @Autowired
    private RateService rateService;


    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String getReportEmployeesPage(Model model,
                                         @RequestParam(required = false) Long periodId,
                                         @RequestParam(required = false) Integer year,
                                         @RequestParam(required = false) Long posId,
                                         @RequestParam(required = false) RateService.RateSort sortType) {

        Period lookupPeriod = periodId != null ? periodHolder.getPeriodById(periodId) :
                periodHolder.getCurrentPeriod();
        int lookupYear = year != null ? year : DateUtils.currentYear();
        RateService.RateSort rateSort = sortType != null ? sortType : RateService.RateSort.DOWNWARDS;
        Collection<Rate> rates = posId == null ? null :
                rateService.getRatesByEmployeePosition(posId, lookupPeriod, lookupYear, rateSort);

        model.addAttribute("rates", rates);
        model.addAttribute("poses", positionService.getAll());
        model.addAttribute("periods", periodHolder.getAllPeriods());
        model.addAttribute("periodNameCode", periodHolder.getPeriodsNameCode());
        model.addAttribute("availYears", periodHolder.getAvailableYears());
        model.addAttribute("selectedPeriodId", periodHolder.getIdOfPeriod(lookupPeriod));
        model.addAttribute("selectedYear", lookupYear);
        model.addAttribute("selectedPosId", posId);

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
