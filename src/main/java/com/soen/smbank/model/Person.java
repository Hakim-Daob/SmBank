/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.smbank.model;

import com.soen.smbank.dao.ObjectDao;
import com.soen.smbank.persistence.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.HibernateException;
import org.hibernate.Session;


/**
 *
 * @author HMD
 */
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable{

    @Id
    @GeneratedValue
    private long userId;
    
    @Column
    private String userName;
    
    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String gender;

    @Column
    private String phoneNumber;
    
    @Column
    private String email;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address userAddress;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(Address userAddress) {
        this.userAddress = userAddress;
    }


    public long saveUser()  {
        ObjectDao<Person> userDao = new ObjectDao<Person>();
        return userDao.addObject(this);
    }

    public void updateUser() throws IllegalAccessException, InvocationTargetException {
        ObjectDao<Person> userDao = new ObjectDao<Person>();
        userDao.updateObject(this, this.getUserId(), Person.class);
    }

    public void deleteUser() throws IllegalAccessException, InvocationTargetException {
        ObjectDao<Person> userDao = new ObjectDao<Person>();
        userDao.deleteObject(this, this.getUserId(), Person.class);
    }

    public static Person getUserById(long id) {
        Person userHolder = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            userHolder = (Person) session.get(Person.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return userHolder;
    }

    public static ArrayList<Person> getUsers() {
        ArrayList<Person> users;
        ObjectDao userDao = new ObjectDao();
        users = userDao.getAllObjects("User");
        return users;
    }
}
