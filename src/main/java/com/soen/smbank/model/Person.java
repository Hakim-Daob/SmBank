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


/**
 *
 * @author HMD
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person implements Serializable{

    @Id
    @GeneratedValue
    private long userId;
    
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
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


    public void saveUser()  {
        ObjectDao<Person> userDao = new ObjectDao<Person>();
        userDao.addObject(this);
    }

    public void updateUser() {
        ObjectDao<Person> userDao = new ObjectDao<Person>();
        userDao.updateObject(this, this.getUserId(), Person.class);
    }

    public void deleteUser()  {
        ObjectDao<Person> userDao = new ObjectDao<Person>();
        userDao.deleteObject(this, this.getUserId(), Person.class);
    }

    public static Person getUserById(long id) {
        ObjectDao<Person> dao = new ObjectDao<Person>();
        return dao.getObjectById(id, Person.class);
    }

    public static ArrayList<Person> getUsers() {
        ObjectDao<Person> dao = new ObjectDao<Person>();
        return dao.getAllObjects(Person.class, "Person");
        
    }
}
