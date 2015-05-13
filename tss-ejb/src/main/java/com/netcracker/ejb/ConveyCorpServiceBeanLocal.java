/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import com.netcracker.entity.Address;
import com.netcracker.entity.Route;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import java.util.List;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author Lis
 */
public interface ConveyCorpServiceBeanLocal  extends EJBLocalObject  {
    
     public Integer addCorpService(User user,List<Route> routes, List<Address> addFrom,
			Address addTo, TaxiOrder taxiOrder);
}
