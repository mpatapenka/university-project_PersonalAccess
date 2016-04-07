package org.diploma.personalaccess.service.impl;

import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.bean.Rate;
import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.entity.UserIndex;
import org.diploma.personalaccess.repository.UserIndexRepository;
import org.diploma.personalaccess.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

/**
 * Rate service default implementation
 *
 * @author Maksim Patapenka
 */
@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private UserIndexRepository userIndexRepository;


    @Override
    public Collection<Rate> getRatesByEmployeePosition(long positionId, Period period, int year,
                                                       RateService.RateSort sortType) {
        Date startPeriod = period.getStartDateForYear(year);
        Date endPeriod = period.getEndDateForYear(year);
        List<UserIndex> userIndexes = userIndexRepository.findByUserFormPositionIdAndFillDateBetween(positionId,
                startPeriod, endPeriod);

        Map<User, Rate> rateContainer = new HashMap<>();
        for (UserIndex userIndex : userIndexes) {
            User user = userIndex.getUser();
            if (!rateContainer.containsKey(user)) {
                Rate rate = new Rate();
                rate.setUser(user);
                rate.setRate(userIndex.getSelfEstimate());

                rateContainer.put(user, rate);
            } else {
                Rate rate = rateContainer.get(user);
                rate.setRate(rate.getRate() + userIndex.getSelfEstimate());
            }
        }

        List<Rate> rates = new ArrayList<>(rateContainer.values());
        Collections.sort(rates, sortType.getComparator());
        return rates;
    }

}
