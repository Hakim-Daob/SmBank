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
@Table
@PrimaryKeyJoinColumn(name = "accountId")

public class ChequingAccount extends Account implements Serializable {

   

    @Override
    public void saveAccount() {
        ObjectDao<ChequingAccount> accountDao = new ObjectDao<ChequingAccount>();
         accountDao.addObject(this);
    }

    @Override
    public void updateAccount()  {
        ObjectDao<ChequingAccount> checkingAccountDao = new ObjectDao<ChequingAccount>();
         checkingAccountDao.updateObject(this, this.getAccountId(), ChequingAccount.class);
//        String setString = " balance = " + this.getBalance();
//       // setString += 
//        String whereString= " account.accountId = " + this.getAccountId();
//        checkingAccountDao.updateUsingSQL("ChequingAccount account ", setString, whereString);   
    }

    @Override
    public void deleteAccount()  {
        ObjectDao<ChequingAccount> checkingAccountDao = new ObjectDao<ChequingAccount>();
        checkingAccountDao.deleteObject(this, this.getAccountId(), ChequingAccount.class);
    }

    public static ChequingAccount getCheckingAccountById(long id) {
       
        ObjectDao<ChequingAccount> dao = new ObjectDao<ChequingAccount>();
        return dao.getObjectById(id, ChequingAccount.class);
    }

    public static ArrayList<ChequingAccount> getCheckingAccounts() {
       ObjectDao<ChequingAccount> dao = new ObjectDao<ChequingAccount>();
        return dao.getAllObjects(ChequingAccount.class, "ChequingAccount");
    }

    @Override
    public boolean withdraw(double amount,String description) throws IllegalAccessException, InvocationTargetException {
        boolean isDone = false;

        if (this.getBalance() >= amount) {
            super.withdraw(amount,description);
            isDone = true;
        }
        return isDone;
    }
}
