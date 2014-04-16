/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.smbank.model;

import com.soen.smbank.dao.ObjectDao;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
public class Payee implements Serializable {

    @Id
    @GeneratedValue
    private Long payeeId;
    private String name;

    public Long getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Hibernate Methods
    public void savePayee() {
        ObjectDao<Payee> accountDao = new ObjectDao<Payee>();
        accountDao.addObject(this);
    }

    public void updatePayee() {
        ObjectDao<Payee> payeeDao = new ObjectDao<Payee>();
        payeeDao.updateObject(this, this.getPayeeId(), Payee.class);
    }

    public void deletePayee() {
        ObjectDao<Payee> payeeDao = new ObjectDao<Payee>();
        payeeDao.deleteObject(this, this.getPayeeId(), Payee.class);
    }

    public static Payee getPayeeById(long id) {
        ObjectDao<Payee> dao = new ObjectDao<Payee>();
        return dao.getObjectById(id, Payee.class);
    }

    public static ArrayList<Payee> getPayees() {
        ObjectDao<Payee> dao = new ObjectDao<Payee>();
        return dao.getAllObjects(Payee.class, "Payee");

    }

    public static Payee getPayeeByName(String payeeName) {
        ObjectDao<Payee> dao = new ObjectDao<Payee>();
        ArrayList<Payee> payees = null;
        payees = dao.getAllObjectsByCondition(" Payee ", " name = " + payeeName);

        return payees.get(0);
    }
}
