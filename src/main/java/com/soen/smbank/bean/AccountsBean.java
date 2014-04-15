/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.smbank.bean;

import com.soen.smbank.model.Account;
import com.soen.smbank.model.Client;
import com.soen.smbank.model.ClosedTermInvestment;
import com.soen.smbank.model.InvestmentAccount;
import com.soen.smbank.model.OpenTermInvestment;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;

/**
 *
 * @author pr_danie
 */
@ManagedBean
@RequestScoped
public class AccountsBean {

    /**
     * Creates a new instance of AccountsBean
     */
    public AccountsBean() {
    }

    private ArrayList<Account> personalAccountsList;
    private ArrayList<Account> investmentAccountsList;
    private Date inputDate;

    public ArrayList<Account> getPersonalAccountsList() {
        populatePersonalAccounts();
        return personalAccountsList;
    }

    public void setPersonalAccountsList(ArrayList<Account> personalAccountsList) {
        this.personalAccountsList = personalAccountsList;
    }

    public ArrayList<Account> getInvestmentAccountsList() {
        populateInvestmentAccounts();
        return investmentAccountsList;
    }

    public void setInvestmentAccountsList(ArrayList<Account> investmentAccountsList) {
        this.investmentAccountsList = investmentAccountsList;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public void populatePersonalAccounts() {
        Client client = getClientfromSession();
        ArrayList<Account> allAccounts = Account.getAllClientAccounts(client.getUserId());
        ArrayList<Account> personalAccounts = (ArrayList) Account.getPersonalAccount(allAccounts);
        personalAccountsList = personalAccounts;
    }

    public void populateInvestmentAccounts() {
        Client client = getClientfromSession();
        ArrayList<Account> allAccounts = Account.getAllClientAccounts(client.getUserId());
        ArrayList<Account> investmentAccounts = (ArrayList) Account.getInvestmentAccounts(allAccounts);
        investmentAccountsList = investmentAccounts;
    }

    public Client getClientfromSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        String clientNumber = (String) session.getAttribute("clientNumber");
        return Client.getClientByAccountNumber(clientNumber);
    }

    public String naviagateToAccount() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> parameters = context.getExternalContext().getRequestParameterMap();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        session.setAttribute("selectedAccountId", parameters.get("selectedAccountId"));
        return "accountsSummary";
    }

    public void calculateReturns() {

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> parameters = context.getExternalContext().getRequestParameterMap();

        InvestmentAccount investAcc = InvestmentAccount.getInvestmentAccountById(Long.parseLong(parameters.get("selectedAccountId")));
        if (investAcc.getInvestmentPlan() instanceof OpenTermInvestment) {
            context.addMessage("displayReturns", new FacesMessage("Your Profit as per today is " + investAcc.calculateReturnOfInvestmentForOpenTermInvestment(new DateTime(inputDate))));
        } else if (investAcc.getInvestmentPlan() instanceof ClosedTermInvestment) {
            context.addMessage("displayReturns", new FacesMessage("Your Profit as per today is " + investAcc.calculateReturnOfInvestmentForClosedTermInvestment(new DateTime(inputDate))));
        }
    }
}
