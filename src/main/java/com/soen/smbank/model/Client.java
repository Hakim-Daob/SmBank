/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.smbank.model;

import com.soen.smbank.dao.ObjectDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
public class Client extends User implements Serializable {

    private long age;

    @OneToMany(mappedBy = "client")

    private List<Account> accounts;

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public void saveUser() {
        ObjectDao<Client> userDao = new ObjectDao<Client>();
        userDao.addObject(this);
    }

    @Override
    public void updateUser() {
        ObjectDao<Client> userDao = new ObjectDao<Client>();
        userDao.updateObject(this, this.getUserId(), Client.class);
    }

    @Override
    public void deleteUser() {
        ObjectDao<Client> userDao = new ObjectDao<Client>();
        userDao.deleteObject(this, this.getUserId(), Client.class);
    }

    public static Client getClientsById(long id) {
        ObjectDao<Client> dao = new ObjectDao<Client>();
        return dao.getObjectById(id, Client.class);

    }

    public static ArrayList<Client> getClients() {
        ObjectDao<Client> dao = new ObjectDao<Client>();
        return dao.getAllObjects(Client.class, "Client");

    }

    public static Client getClientByAccountNumber(String clientNumber) {
        ObjectDao<Client> dao = new ObjectDao<Client>();
        ArrayList<Client> client = null;
        client = dao.getAllObjectsByCondition(" Client ", " userName = " + clientNumber);

        return client.get(0);
    }

}
