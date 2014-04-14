/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soen.smbank.run;

import com.soen.smbank.model.*;
import com.soen.smbank.utils.DateUtil;
import org.joda.time.DateTime;

/**
 *
 * @author Khalid
 */
public class InvestmentTesting {
    public static void main(String[] args) {
        
        Client cl = Client.getClientsById(1);
        InvestmentPlan ip = InvestmentPlan.getInvestmentPlanById(1);
        ClosedTermInvestment cti = new ClosedTermInvestment(.3, 60, .55);
        cti.saveInvestmentPlan();
        
        InvestmentAccount ia = new InvestmentAccount(DateTime.now(), DateUtil.addDays(DateTime.now(), 60), cti);
        ia.setAccountNumber("Investment001");
        ia.setClient(cl);
        ia.saveAccount();
        
       
        
        
    }
 
}
