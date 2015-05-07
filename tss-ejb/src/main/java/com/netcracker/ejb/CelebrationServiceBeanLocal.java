package com.netcracker.ejb;

import com.netcracker.entity.Address;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.helper.DriverCar;

import javax.ejb.EJBLocalObject;
import java.util.List;

/**
 * @author Illia Rudenko
 */
public interface CelebrationServiceBeanLocal extends EJBLocalObject {

    public void addCelebrationService(TaxiOrder taxiOrder,
                                      Address fromAddress,
                                      List<DriverCar> driverCars,
                                      Integer driversAmount,
                                      Integer duration);
}
