/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import com.netcracker.dao.TariffDAO;
import com.netcracker.entity.Tariff;
import com.netcracker.entity.TaxiOrder;
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

    public double calculatePrice(float distance, Date orderTime, TaxiOrder taxiOrder) {
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
            if ((taxiOrder.getAnimalTransport() != null) && (taxiOrder.getConditioner() != null) && (taxiOrder.getWifi() != null) && (taxiOrder.getSmoke() != null) && (taxiOrder.getCarCategory() != null)) {
                switch (taxiOrder.getCarCategory()) {
                    case 1:
                        tariff = tariffDAO.findByTariffName("economy");
                        orderPrice = (orderPrice + tariff.getPlusCoef()) * tariff.getMultipleCoef();
                        break;
                    case 2:
                        tariff = tariffDAO.findByTariffName("business");
                        orderPrice = (orderPrice + tariff.getPlusCoef()) * tariff.getMultipleCoef();
                        break;
                    case 3:
                        tariff = tariffDAO.findByTariffName("van");
                        orderPrice = (orderPrice + tariff.getPlusCoef()) * tariff.getMultipleCoef();
                        break;
                    case 4:
                        tariff = tariffDAO.findByTariffName("cargo");
                        orderPrice = (orderPrice + tariff.getPlusCoef()) * tariff.getMultipleCoef();
                        break;
                    default:
                        break;
                }
                if (taxiOrder.getWifi()) {
                    tariff = tariffDAO.findByTariffName("wi-fi");
                    orderPrice = (orderPrice + tariff.getPlusCoef()) * tariff.getMultipleCoef();
                }
                if (taxiOrder.getSmoke()) {
                    tariff = tariffDAO.findByTariffName("no_smoked");
                    orderPrice = (orderPrice + tariff.getPlusCoef()) * tariff.getMultipleCoef();

                }
                if (taxiOrder.getAnimalTransport()) {
                    tariff = tariffDAO.findByTariffName("animal");
                    orderPrice = (orderPrice + tariff.getPlusCoef()) * tariff.getMultipleCoef();

                }
                if (taxiOrder.getConditioner()) {
                    tariff = tariffDAO.findByTariffName("conditioner");
                    orderPrice = (orderPrice + tariff.getPlusCoef()) * tariff.getMultipleCoef();

                }
            }
        } finally {
            if (tariffDAO != null) {
                tariffDAO.close();
            }
        }

        return orderPrice;
    }

    public float calculateCelebrationServicePrice(int carsAmount, int duration, Date orderTime) {
        float orderPrice = 0;
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(orderTime);
        Tariff tariff;

        TariffDAO tariffDAO = new TariffDAO();
        if (((calendar.get(Calendar.HOUR_OF_DAY) >= 22) && (calendar.get(Calendar.HOUR_OF_DAY) <= 24))
                || ((calendar.get(Calendar.HOUR_OF_DAY) >= 0) && (calendar.get(Calendar.HOUR_OF_DAY) <= 7))) {
            tariff = tariffDAO.findByTariffName("night");
            orderPrice = (orderPrice + tariff.getPlusCoef()) * tariff.getMultipleCoef();
        }
        if ((calendar.get(Calendar.HOUR_OF_DAY) >= 9) && (calendar.get(Calendar.HOUR_OF_DAY) <= 11)) {
            tariff = tariffDAO.findByTariffName("rush_hour");
            orderPrice = (orderPrice + tariff.getPlusCoef()) * tariff.getMultipleCoef();
        }

        tariff = tariffDAO.findByTariffName("per_car");
        orderPrice = (orderPrice + tariff.getPlusCoef()) * tariff.getMultipleCoef();

        tariff = tariffDAO.findByTariffName("per_hour");
        orderPrice = (orderPrice + tariff.getPlusCoef()) * tariff.getMultipleCoef();

        return orderPrice;
    }

    @Override
    public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {
    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {

    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {

    }

}
