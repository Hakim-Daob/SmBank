/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.smbank.model;

import com.soen.smbank.dao.ObjectDao;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Peyman
 */
@Entity
@Table
public class CreditPlan implements Serializable {

    @Id
    @GeneratedValue
    protected Long creditPlanId;
    private double cashAdvanceInterest;
    private double interestRate;

    @OneToMany(mappedBy = "creditPlan", cascade = CascadeType.ALL)
    private List<CreditAccount> creditAccounts;

    public CreditPlan() {
        this.creditAccounts=new ArrayList<CreditAccount>();
    }

    public CreditPlan(double cashAdvanceInterest, double interestRate) {
        this.cashAdvanceInterest = cashAdvanceInterest;
        this.interestRate = interestRate;
        this.creditAccounts=new ArrayList<CreditAccount>();
    }

    public Long getCreditPlanId() {
        return creditPlanId;
    }

    public void setCreditPlanId(Long creditPlanId) {
        this.creditPlanId = creditPlanId;
    }

    public double getCashAdvanceInterest() {
        return cashAdvanceInterest;
    }

    public void setCashAdvanceInterest(double cashAdvanceInterest) {
        this.cashAdvanceInterest = cashAdvanceInterest;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public List<CreditAccount> getCreditAccounts() {
        return creditAccounts;
    }

    public void setCreditAccounts(List<CreditAccount> creditAccounts) {
        this.creditAccounts = creditAccounts;
    }

    public void saveCreditPlan() {
        ObjectDao<CreditPlan> accountDao = new ObjectDao<CreditPlan>();
         accountDao.addObject(this);
    }

    public void updateCreditPlan() throws IllegalAccessException, InvocationTargetException {
        ObjectDao<CreditPlan> creditPlanDao = new ObjectDao<CreditPlan>();
        creditPlanDao.updateObject(this, this.getCreditPlanId(), CreditPlan.class);
    }

    public void deleteCreditPlan() throws IllegalAccessException, InvocationTargetException {
        ObjectDao creditPlanDao = new ObjectDao();
        creditPlanDao.deleteObject(this, this.getCreditPlanId(), CreditPlan.class);
    }

    public static CreditPlan getCreditPlanById(long id) {
         ObjectDao<CreditPlan> dao = new ObjectDao<CreditPlan>();
        return dao.getObjectById(id, CreditPlan.class);
    }

    public static ArrayList<CreditPlan> getCreditPlans() {
        ObjectDao<CreditPlan> dao = new ObjectDao<CreditPlan>();
        return dao.getAllObjects(CreditPlan.class, "CreditPlan");
    }

}