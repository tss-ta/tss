/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import com.netcracker.entity.TaxiOrder;
import java.util.Date;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author Виктор
 */
public interface PriceBeanLocal extends EJBLocalObject {

    public double calculatePrice(float distance, Date orderTime, TaxiOrder taxiOrder);

}
