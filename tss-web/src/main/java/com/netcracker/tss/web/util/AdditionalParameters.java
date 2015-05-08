/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.tss.web.util;

import com.netcracker.entity.TaxiOrder;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Виктор
 */
public class AdditionalParameters {

    public static TaxiOrder taxiOrderAddParameters(HttpServletRequest req) {
        Integer carType = checkString(req.getParameter("carType"));
        Integer wayOfPayment = checkString(req.getParameter("paymentType"));
        Boolean driversGender = checkDriversGender(req
                .getParameter("driverGender"));
        Integer musicType = checkString(req.getParameter("musicType"));
        String[] addParameters = req.getParameterValues("addOptions");
        Boolean wifi = null;
        Boolean animal = null;
        Boolean noSmokeDriver = null;
        Boolean conditioner = null;
        if (addParameters != null) {
            for (String st : addParameters) {
                if ("wifi".equals(st)) {
                    wifi = Boolean.TRUE;
                }
                if ("animal".equals(st)) {
                    animal = Boolean.TRUE;
                }
                if ("nosmoke".equals(st)) {
                    noSmokeDriver = Boolean.TRUE;
                }
                if ("conditioner".equals(st)) {
                    conditioner = Boolean.TRUE;
                }
            }
        }
        return new TaxiOrder(wayOfPayment, musicType, driversGender,
                noSmokeDriver, carType, animal, wifi, conditioner);
    }

    private static Boolean checkDriversGender(String s) {
        if (!"".equals(s)) {
            if ("male".equals(s)) {
                return true;
            } else {
                return false;
            }
        }
        return null;
    }

    private static Integer checkString(String s) {
        if (!"".equals(s)) {
            return Integer.parseInt(s);
        }
        return null;
    }
}
