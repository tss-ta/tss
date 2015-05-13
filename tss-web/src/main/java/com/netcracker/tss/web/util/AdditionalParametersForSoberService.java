/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.tss.web.util;

import com.netcracker.entity.TaxiOrder;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Lis
 */
public class AdditionalParametersForSoberService {
       public static TaxiOrder taxiOrderAddParameters(HttpServletRequest req) {
        Integer wayOfPayment = checkString(req.getParameter("paymentType"));
        Boolean driversGender = checkDriversGender(req
                .getParameter("driverGender"));
        String[] addParameters = req.getParameterValues("addOptions");
        if (addParameters == null) {
            addParameters = req.getParameterValues("addOptions[]");
        }
        Boolean noSmokeDriver = Boolean.FALSE;
        if (addParameters != null) {
            for (String st : addParameters) {
                if ("nosmoke".equals(st)) {
                    noSmokeDriver = Boolean.TRUE;
                }
            }
        }
        return new TaxiOrder(wayOfPayment, null, driversGender,
                noSmokeDriver, 1, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
    }

    private static Boolean checkDriversGender(String s) {
        if ((s != null) && (!"".equals(s))) {
            if ("male".equals(s)) {
                return true;
            } else {
                return false;
            }
        }
        return null;
    }

    private static Integer checkString(String s) {
        if ((s != null) && (!"".equals(s))) {
            return Integer.parseInt(s);
        }
        return null;
    }
}
