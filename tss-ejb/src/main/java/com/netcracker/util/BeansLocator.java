/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.util;

import com.netcracker.ejb.CarBeanLocal;
import com.netcracker.ejb.CarBeanLocalHome;
import com.netcracker.ejb.GroupBeanLocal;
import com.netcracker.ejb.GroupBeanLocalHome;
import com.netcracker.ejb.UserBeanLocal;
import com.netcracker.ejb.UserBeanLocalHome;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author maks
 */
public class BeansLocator {
    
    private static BeansLocator locator = null;
    
    public static BeansLocator getInstance () {
        if (locator == null) {
            locator = new BeansLocator();
        }
        return locator;
    }
    
    public Object lookup (String name) {
        Context context;
        try {
            context = new InitialContext();
            return context.lookup(name);
        } catch (NamingException ex) {
            Logger.getLogger(BeansLocator.class.getName()).log(Level.SEVERE,
                    "Can't find object with name: " + name, ex);
            throw new RuntimeException("Can't find object with name: " + name);// maybe have to create custom exception?
        }
    }
    
    public GroupBeanLocal getGroupBean() {
            GroupBeanLocalHome groupBeanLocalHome = (GroupBeanLocalHome) lookup("java:app/tss-ejb/GroupBean!com.netcracker.ejb.GroupBeanLocalHome"); //have to catch or throws ClassCastException?
            return groupBeanLocalHome.create();
    }

    public UserBeanLocal getUserBean() {
            UserBeanLocalHome customerBeanLocalHome = (UserBeanLocalHome) lookup("java:app/tss-ejb/UserBean!com.netcracker.ejb.UserBeanLocalHome");
            return customerBeanLocalHome.create();
    }
    
    public CarBeanLocal getCarBean() {
            CarBeanLocalHome carBeanLocalHome = (CarBeanLocalHome) lookup("java:app/tss-ejb/CarBean!com.netcracker.ejb.CarBeanLocalHome");
            return carBeanLocalHome.create();
    }


}
