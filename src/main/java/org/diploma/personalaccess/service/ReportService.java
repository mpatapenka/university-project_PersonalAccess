package org.diploma.personalaccess.service;

import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.bean.ReportHolder;
import org.diploma.personalaccess.entity.Position;

import java.util.List;

public interface ReportService {

    List<ReportHolder> getIndexGroups(Position position, Period period, int year);

}
