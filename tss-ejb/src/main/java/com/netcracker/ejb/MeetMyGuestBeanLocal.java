/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import com.netcracker.entity.Address;
import com.netcracker.entity.Route;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author Виктор
 */
public interface MeetMyGuestBeanLocal extends EJBLocalObject {

    public void addMeetMyGuestService(User user, Route route, Address addFrom,
            Address addTo, TaxiOrder taxiOrder, String guestName);
}
