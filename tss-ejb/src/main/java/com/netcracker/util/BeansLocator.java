/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.util;

import com.netcracker.ejb.*;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBLocalHome;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author maks
 */
public class BeansLocator {

    private static final String BEAN_LOCAL_CREATE_METHOD_NAME = "create";

    private static Logger log = Logger.getLogger(BeansLocator.class.getName());
    private static BeansLocator locator = null;
    private JndiNameBuilder nameBuilder = new JndiNameBuilder();

    public static BeansLocator getInstance() {
        if (locator == null) {
            locator = new BeansLocator();
        }
        return locator;
    }

    public Object lookup(String name) {
        Context context;
        try {
            context = new InitialContext();
            return context.lookup(name);
        } catch (NamingException ex) {
            log.log(Level.SEVERE, "Can't find object with name: " + name, ex);
            throw new RuntimeException("Can't find object with name: " + name);// maybe have to create custom exception?
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> beanLocal) {
        Object localHome = lookup(nameBuilder.buildEjbName(beanLocal));
        T result = null;
        try {
            result = (T) localHome.getClass().getMethod(BEAN_LOCAL_CREATE_METHOD_NAME).invoke(localHome);
        } catch (Exception e) {
//           Todo
            log.log(Level.SEVERE, "Can't find or ivoke object with name: " + beanLocal.getName(), e);
        }
        return result;
    }

//    public GroupBeanLocal getGroupBean() {
//        GroupBeanLocalHome groupBeanLocalHome = (GroupBeanLocalHome) lookup("java:app/tss-ejb/GroupBean!com.netcracker.ejb.GroupBeanLocalHome"); //have to catch or throws ClassCastException?
//        return groupBeanLocalHome.create();
//    }
    @Deprecated
    public UserBeanLocal getUserBean() {
        UserBeanLocalHome customerBeanLocalHome = (UserBeanLocalHome) lookup("java:app/tss-ejb/UserBean!com.netcracker.ejb.UserBeanLocalHome");
        return customerBeanLocalHome.create();
    }

    @Deprecated
    public CarBeanLocal getCarBean() {
        CarBeanLocalHome carBeanLocalHome = (CarBeanLocalHome) lookup("java:app/tss-ejb/CarBean!com.netcracker.ejb.CarBeanLocalHome");
        return carBeanLocalHome.create();
    }

    @Deprecated
    public TariffBeanLocal getTariffBean() {
        TariffBeanLocalHome tariffBeanLocalHome = (TariffBeanLocalHome) lookup("java:app/tss-ejb/TariffBean!com.netcracker.ejb.TariffBeanLocalHome");
        return tariffBeanLocalHome.create();
    }

    @Deprecated
    public DriverLocal getDriverBean() {
        DriverLocalHome driverLocalHome = (DriverLocalHome) lookup("java:app/tss-ejb/DriverBean!com.netcracker.ejb.DriverLocalHome");
        return driverLocalHome.create();
    }

//    public ReportsBeanLocal getReportsBean() {
//        ReportsBeanLocalHome reportsBeanLocalHome = (ReportsBeanLocalHome) lookup("java:app/tss-ejb/ReportsBean!com.netcracker.ejb.ReportsBeanLocalHome");
//        return reportsBeanLocalHome.create();
//    }
    @Deprecated
    private TaxiOrderBeanLocal getTaxiOrderBean() {
        TaxiOrderBeanLocalHome taxiOrderBeanLocalHome = (TaxiOrderBeanLocalHome) lookup("java:app/tss-ejb/TaxiOrderBean!com.netcracker.ejb.TaxiOrderBeanLocalHome");
        return taxiOrderBeanLocalHome.create();

    }
}
