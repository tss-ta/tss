/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import java.util.Date;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author Виктор
 */
public interface PriceBeanLocal extends EJBLocalObject {

    public double calculatePrice(float distance, Date orderTime, TaxiOrder taxiOrder, User user);

    public float calculateCelebrationServicePrice(int carsAmount, int duration, Date orderTime, User user);
    
    public double calculatePriceForSoberService(float distance, Date orderTime, TaxiOrder taxiOrder, User user);
    
    public double calculatePriceForCceService(float distance, Date orderTime, TaxiOrder taxiOrder, User user);

}
