package org.diploma.personalaccess.service.impl;

import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.bean.ReportHolder;
import org.diploma.personalaccess.entity.Faculty;
import org.diploma.personalaccess.entity.Index;
import org.diploma.personalaccess.entity.Position;
import org.diploma.personalaccess.repository.FacultyRepository;
import org.diploma.personalaccess.repository.IndexRepository;
import org.diploma.personalaccess.repository.UserIndexRepository;
import org.diploma.personalaccess.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private IndexRepository indexRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private UserIndexRepository userIndexRepository;


    @Override
    @Transactional
    public List<ReportHolder> getIndexGroups(Position position, Period period, int year) {
        List<ReportHolder> reports = new ArrayList<>();

        // Return empty list if we have wrong parameters
        if (position == null || period == null) {
            return reports;
        }

        Set<Index> availableIndexes = indexRepository.findByAvailablePositionsAndIsArchived(position, false);
        List<Faculty> availableFaculties = new ArrayList<>(facultyRepository.findAll()); // need copy
        Date start = period.getStartDateForYear(year);
        Date end = period.getEndDateForYear(year);

        for (Index availableIndex : availableIndexes) {
            ReportHolder holder = ReportHolder.buildSimpleReportHolder();
            holder.getIndexes().add(availableIndex);
            holder.setUsers(null); // not needed for this report type
            holder.setFaculties(availableFaculties);

            Double sum = 0d;
            for (Faculty availableFaculty : availableFaculties) {
                Double facultyRate = userIndexRepository
                        .sumLeadEstimateByIndexAndUserFormFacultyAndUserFormPositionAndFillDateBetween(
                                availableIndex.getId(), position.getId(), availableFaculty.getId(), start, end);

                facultyRate = facultyRate != null && facultyRate >= 0 ? facultyRate : 0d;

                holder.getRates().add(facultyRate);
                sum += facultyRate;
            }

            Double withoutFacultyRate = userIndexRepository
                    .sumLeadEstimateByIndexAndUserFormFacultyNullAndUserFormPositionAndFillDateBetween(
                            availableIndex.getId(), position.getId(), start, end);

            withoutFacultyRate = withoutFacultyRate!= null && withoutFacultyRate >= 0 ? withoutFacultyRate : 0d;

            holder.getRates().add(withoutFacultyRate);
            sum += withoutFacultyRate;

            holder.setRatesSum(sum);

            reports.add(holder);
        }

        Faculty emptyFaculty = new Faculty();
        emptyFaculty.setShortName("Остальные");

        availableFaculties.add(emptyFaculty);

        return reports;
    }

}
