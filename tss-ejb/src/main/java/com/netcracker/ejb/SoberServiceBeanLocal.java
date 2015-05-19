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
public interface SoberServiceBeanLocal   extends EJBLocalObject  {
         public Integer addSoberService(User user,Route r, Address addFrom,
			Address addTo, TaxiOrder taxiOrder);
}
