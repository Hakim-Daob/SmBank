/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soen.smbank.model;

import com.soen.smbank.dao.ObjectDao;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class UserRole implements Serializable {
    @Id
    private double userId;
    @Id
    private double roleId;

    public double getUserId() {
        return userId;
    }

    public void setUserId(double userId) {
        this.userId = userId;
    }

    public double getRoleId() {
        return roleId;
    }

    public void setRoleId(double roleId) {
        this.roleId = roleId;
    }
     
     
      
    
}
