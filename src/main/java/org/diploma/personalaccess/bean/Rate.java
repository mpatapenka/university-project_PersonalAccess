package org.diploma.personalaccess.bean;

import org.diploma.personalaccess.entity.User;
import org.springframework.core.style.ToStringCreator;

import java.io.Serializable;

/**
 * Simple bean object representing an rate of user.
 *
 * @author Maksim Patapenka
 */
public class Rate implements Serializable {

    /**
     * User info
     */
    private User user;

    /**
     * User rate
     */
    private double rate;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }


    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("user", user)
                .append("rate", rate)
                .toString();
    }

}
