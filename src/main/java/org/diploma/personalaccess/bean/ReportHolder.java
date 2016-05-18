package org.diploma.personalaccess.bean;

import org.diploma.personalaccess.entity.Faculty;
import org.diploma.personalaccess.entity.Index;
import org.diploma.personalaccess.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ReportHolder {

    private List<Index> indexes;
    private List<Faculty> faculties;
    private List<Double> rates;
    private List<User> users;
    private double ratesSum;

    public List<Index> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<Index> indexes) {
        this.indexes = indexes;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public List<Double> getRates() {
        return rates;
    }

    public void setRates(List<Double> rates) {
        this.rates = rates;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public double getRatesSum() {
        return ratesSum;
    }

    public void setRatesSum(double ratesSum) {
        this.ratesSum = ratesSum;
    }

    public static ReportHolder buildSimpleReportHolder() {
        ReportHolder holder = new ReportHolder();
        holder.indexes = new ArrayList<>();
        holder.faculties = new ArrayList<>();
        holder.rates = new ArrayList<>();
        holder.users = new ArrayList<>();
        holder.ratesSum = 0;

        return holder;
    }

}
