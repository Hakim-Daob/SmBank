package com.soen.smbank.model;

import com.soen.smbank.dao.ObjectDao;
import com.soen.smbank.utils.JodaDateTimeConverter;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.persistence.*;
import org.joda.time.DateTime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
@Entity
public class AccountTransaction implements Serializable {

    @Id
    @GeneratedValue
    
    private Long transactionId;

    @Convert(converter= JodaDateTimeConverter.class)
    private DateTime transactionTime;


    private String description;

    private double debit;
    private String formattedDebit;
    private double credit;
    private String formattedCredit;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account sourceAccount;

    public AccountTransaction() {
    }

    public AccountTransaction(Account sourceAccount, double debit, double credit, String description) {
        this.sourceAccount = sourceAccount;
        this.description = description;
        this.debit = debit;
        if (this.debit == 0 ){
        this.formattedDebit = formatDoubleToCurrency(-1*debit);
        }else{
        this.formattedDebit = formatDoubleToCurrency(debit);
        }
        this.credit = credit;
        this.formattedCredit = formatDoubleToCurrency(credit);
        this.transactionTime = DateTime.now();

    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public DateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(DateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getFormattedDebit() {
        return formattedDebit;
    }

    public void setFormattedDebit(String formattedDebit) {
        this.formattedDebit = formattedDebit;
    }

    public String getFormattedCredit() {
        return formattedCredit;
    }

    public void setFormattedCredit(String formattedCredit) {
        this.formattedCredit = formattedCredit;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String formatDoubleToCurrency(double amount) {
        if (amount == 0) {
            return "";
        } else {
            String currencyString = NumberFormat.getCurrencyInstance(Locale.CANADA).format(amount);
            //return currencyString.replaceAll("\\.00", "");
            return currencyString;

        }
    }

    public void saveTransaction() {
            ObjectDao<AccountTransaction> accountDao = new ObjectDao<AccountTransaction>();
        accountDao.addObject(this);
    }

    public void updateTransaction()  {
        ObjectDao<AccountTransaction> accountDao = new ObjectDao<AccountTransaction>();
        accountDao.updateObject(this, this.getTransactionId(), AccountTransaction.class);
    }

    public void deleteTransaction()  {
        ObjectDao<AccountTransaction> accountDao = new ObjectDao<AccountTransaction>();
        accountDao.deleteObject(this, this.getTransactionId(), AccountTransaction.class);
    }

    public static AccountTransaction getTransactionById(long id) {
       
       ObjectDao<AccountTransaction> dao = new ObjectDao<AccountTransaction>();
        return dao.getObjectById(id, AccountTransaction.class);
    }

    public static ArrayList<AccountTransaction> getTransactions() {
         ObjectDao<AccountTransaction> dao = new ObjectDao<AccountTransaction>();
        return dao.getAllObjects(AccountTransaction.class, "AccountTransaction");
    }

    public static ArrayList<AccountTransaction> getAccountTransactions(String accountNumber) {
        ArrayList<AccountTransaction> transactions;
        ObjectDao accountTransactionDao = new ObjectDao();
        transactions = accountTransactionDao.getAllObjectsByCondition("AccountTransaction", "sourceAccount_accountId = " + accountNumber);
        return transactions;
    }
}
