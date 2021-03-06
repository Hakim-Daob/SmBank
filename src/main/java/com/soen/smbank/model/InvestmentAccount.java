/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.smbank.model;

import com.soen.smbank.dao.ObjectDao;
import com.soen.smbank.utils.DateUtil;
import com.soen.smbank.utils.JodaDateTimeConverter;
import com.soen.smbank.utils.RandomUtil;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;
import javax.persistence.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

@Entity
@Table
@PrimaryKeyJoinColumn(name = "accountId")
public class InvestmentAccount extends Account implements Serializable {

    @Convert(converter= JodaDateTimeConverter.class)
    private DateTime startDate;
    @Convert(converter= JodaDateTimeConverter.class)
    private DateTime endDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private InvestmentPlan investmentPlan;
    
    public InvestmentAccount() {
        super();
    }

    public InvestmentAccount(DateTime startDate, DateTime endDate, InvestmentPlan investmentPlan) {
        super();
        this.startDate = startDate;
        this.endDate = endDate;
        this.investmentPlan = investmentPlan;
    }

  

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public InvestmentPlan getInvestmentPlan() {
        return investmentPlan;
    }

    public void setInvestmentPlan(InvestmentPlan investmentPlan) {
        this.investmentPlan = investmentPlan;
    }

    @Override
    public void saveAccount() {
        ObjectDao<InvestmentAccount> accountDao = new ObjectDao<InvestmentAccount>();
        this.endDate = DateUtil.addDays(this.startDate, this.investmentPlan.getDurationInDays());
        accountDao.addObject(this);
    }

    @Override
    public void updateAccount() throws IllegalAccessException, InvocationTargetException {
        ObjectDao<InvestmentAccount> investmentAccountDao = new ObjectDao<InvestmentAccount>();
        this.endDate = DateUtil.addDays(this.startDate, this.investmentPlan.getDurationInDays());
        investmentAccountDao.updateObject(this, this.getAccountId(), InvestmentAccount.class);
    }

    @Override
    public void deleteAccount() throws IllegalAccessException, InvocationTargetException {
        ObjectDao investmentAccountDao = new ObjectDao();
        investmentAccountDao.deleteObject(this, this.getAccountId(), InvestmentAccount.class);
    }

    public static InvestmentAccount getInvestmentAccountById(long id) {
        ObjectDao<InvestmentAccount> dao = new ObjectDao<InvestmentAccount>();
        return dao.getObjectById(id, InvestmentAccount.class);
        
    }

    public static ArrayList<InvestmentAccount> getInvestmentAccounts() {
        ObjectDao<InvestmentAccount> dao = new ObjectDao<InvestmentAccount>();
        return dao.getAllObjects(InvestmentAccount.class, "InvestmentAccount");
    }

    @Override
    public boolean withdraw(double amount, String description) throws IllegalAccessException, InvocationTargetException {
        boolean isDone = false;
        return isDone;
    }

    public double calculateReturnOfInvestment(DateTime today) {
        double result = 0;
        if (this.investmentPlan instanceof ClosedTermInvestment) {
            return calculateReturnOfInvestmentForClosedTermInvestment(today);
        } else if (this.investmentPlan instanceof OpenTermInvestment) {
            return calculateReturnOfInvestmentForOpenTermInvestment(today);
        }
        return result;
    }

    public double calculateReturnOfInvestmentForClosedTermInvestment(DateTime today) {
//        long duration = this.getInvestmentPlan().getDurationInDays();
        double returnOfInvestmentPercent = this.getInvestmentPlan().getInvestmentReturnsPercent();
        double returnOfInvestment = 0;

        int days = DateUtil.DateDifference(this.endDate, today);
        if (days > 0) {
            returnOfInvestment = (returnOfInvestmentPercent * this.getBalance()) + this.getBalance();
        }
        return returnOfInvestment;
    }

    public double calculateReturnOfInvestmentForOpenTermInvestment(DateTime today) {

        double returnOfInvestment = 0;

        int days = DateUtil.DateDifference(this.endDate, today);
        if (days > 0) {
            for (int i = 0; i < days; i++) {
                double returnOfInvestmentPercent = RandomUtil.randomInRange(0.0000001, 0.0003);
                returnOfInvestment = (returnOfInvestmentPercent * this.getBalance()) + this.getBalance();

            }

        }
        return returnOfInvestment;
    }
}
