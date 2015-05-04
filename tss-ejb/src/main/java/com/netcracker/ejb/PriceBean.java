/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import com.netcracker.dao.TariffDAO;
import com.netcracker.entity.Tariff;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 *
 * @author Виктор
 */
public class PriceBean implements SessionBean {

    public double calculatePrice(float distance, Date orderTime) {
        double orderPrice = 0;
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(orderTime);
        TariffDAO tariffDAO = null;
        Tariff tariff = null;
        try {
            tariffDAO = new TariffDAO();
            tariff = tariffDAO.findByTariffName("per_km");
            orderPrice = (distance + tariff.getPlusCoef()) * tariff.getMultipleCoef();
            if (((calendar.get(Calendar.HOUR_OF_DAY) >= 22) && (calendar.get(Calendar.HOUR_OF_DAY) <= 24))
                    || ((calendar.get(Calendar.HOUR_OF_DAY) >= 0) && (calendar.get(Calendar.HOUR_OF_DAY) <= 7))) {
                tariff = tariffDAO.findByTariffName("night");
                orderPrice = (orderPrice + tariff.getPlusCoef()) * tariff.getMultipleCoef();
            }
            if ((calendar.get(Calendar.HOUR_OF_DAY) >= 9) && (calendar.get(Calendar.HOUR_OF_DAY) <= 11)) {
                tariff = tariffDAO.findByTariffName("rush_hour");
                orderPrice = (orderPrice + tariff.getPlusCoef()) * tariff.getMultipleCoef();
            }
        } finally {
            if (tariffDAO != null) {
                tariffDAO.close();
            }
        }

        return orderPrice;
    }

    @Override
    public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
