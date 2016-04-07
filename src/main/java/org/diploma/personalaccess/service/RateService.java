package org.diploma.personalaccess.service;

import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.bean.Rate;

import java.util.Collection;
import java.util.Comparator;

/**
 * Service for working with rates and depend on it items
 *
 * @author Maksim Patapenka
 */
public interface RateService {

    /**
     * Get all rates by position
     *
     * @param positionId position id
     * @param period specified period
     * @param year specified year
     * @param sortType rate sort type
     * @return rate collection
     */
    Collection<Rate> getRatesByEmployeePosition(long positionId, Period period, int year, RateService.RateSort sortType);


    /**
     * Type of rate sorting
     */
    enum RateSort {

        DOWNWARDS {

            public Comparator<Rate> getComparator() {
                return (left, right) -> Double.compare(right.getRate(), left.getRate());
            }

        },

        UPWARDS {

            public Comparator<Rate> getComparator() {
                return (left, right) -> Double.compare(left.getRate(), right.getRate());
            }

        };

        /**
         * Getting comparator for rate objects
         *
         * @return comparator for rate objects
         */
        public abstract Comparator<Rate> getComparator();

    }

}
