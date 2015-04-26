/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;


import com.netcracker.entity.User;
import javax.ejb.EJBLocalObject;


/**
 *
 * @author Виктор
 */

public interface RegistrationBeanLocal extends EJBLocalObject {
    void registrate(User user);
    boolean isUserExist(User user);
}
