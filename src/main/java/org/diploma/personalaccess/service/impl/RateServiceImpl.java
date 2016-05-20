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
    public List<Rate> getRatesByEmployeePosition(long positionId, Period period, int year,
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
                rate.setRate(userIndex.getLeadEstimate());

                rateContainer.put(user, rate);
            } else {
                Rate rate = rateContainer.get(user);
                rate.setRate(rate.getRate() + userIndex.getLeadEstimate());
            }
        }

        List<Rate> rates = new ArrayList<>(rateContainer.values());
        Collections.sort(rates, sortType.getComparator());
        return rates;
    }

    @Override
    public List<Rate> formTop5FromAllRates(List<Rate> allRates) {
        final int TOP5_VALUE = 5;

        List<Rate> result;
        if (allRates.size() > TOP5_VALUE) {
            // We create list with top 5 person, because we have more then 5
            result = new ArrayList<>(allRates.subList(0, TOP5_VALUE));
        } else {
            result = allRates;
        }

        Iterator<Rate> iterator = result.iterator();
        while (iterator.hasNext()) {
            Rate rate = iterator.next();
            if (rate.getRate() < 0) {
                iterator.remove();
            }
        }

        return result;
    }

}
