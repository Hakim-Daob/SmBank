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
import javax.persistence.*;


@Entity
@Table(name = "InvestmentPlan")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class InvestmentPlan implements Serializable{
    
    @Id
    @GeneratedValue
    private Long investmentPlanId;
    
    @Column
     private double penaltyPercent;
    
    @Column
    private int durationInDays;
    
    
    @Column
    private double investmentReturnsPercent;
    

    public Long getInvestmentPlanId() {
        return investmentPlanId;
    }

    public void setInvestmentPlanId(Long id) {
        this.investmentPlanId = id;
    }

    public double getPenaltyPercent() {
        return penaltyPercent;
    }

    public void setPenaltyPercent(double penaltyPercent) {
        this.penaltyPercent = penaltyPercent;
    }

    public double getInvestmentReturnsPercent() {
        return investmentReturnsPercent;
    }

    public void setInvestmentReturnsPercent(double investmentReturnsPercent) {
        this.investmentReturnsPercent = investmentReturnsPercent;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }
      

    public void saveInvestmentPlan()  {
        ObjectDao<InvestmentPlan> investmentPlanDao = new ObjectDao<InvestmentPlan>();
        investmentPlanDao.addObject(this);
    }

    public void updateInvestmentPlan()  {
        ObjectDao<InvestmentPlan> investmentPlanDao = new ObjectDao<InvestmentPlan>();
        investmentPlanDao.updateObject(this, this.getInvestmentPlanId(), InvestmentPlan.class);
    }

    public void deleteInvestmentPlan()  {
        ObjectDao<InvestmentPlan> investmentPlanDao = new ObjectDao<InvestmentPlan>();
        investmentPlanDao.deleteObject(this, this.getInvestmentPlanId(), InvestmentPlan.class);
    }

    public static InvestmentPlan getInvestmentPlanById(long id) {
           ObjectDao<InvestmentPlan> dao = new ObjectDao<InvestmentPlan>();
           return dao.getObjectById(id, InvestmentPlan.class);
    }

    public static ArrayList<InvestmentPlan> getInvestmentPlans() {
         ObjectDao<InvestmentPlan> dao = new ObjectDao<InvestmentPlan>();
        return dao.getAllObjects(InvestmentPlan.class, "InvestmentPlan");
       
    }
}
 


