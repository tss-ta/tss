package com.netcracker.ejb;

import com.netcracker.dao.CelebrationServiceDAO;
import com.netcracker.entity.Address;
import com.netcracker.entity.CelebrationService;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.helper.DriverCar;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.util.List;

/**
 * @author Illia Rudenko
 */
public class CelebrationServiceBean implements SessionBean {
    public CelebrationServiceBean() {
    }



    public void addCelebrationService(TaxiOrder taxiOrder, Address fromAddress, List<DriverCar> driverCars, Integer driversAmount, Integer duration) {
        CelebrationServiceDAO celServiceDAO = null;

        try {
            celServiceDAO = new CelebrationServiceDAO();

            CelebrationService celService = new CelebrationService();
            celService.setTaxiOrder(taxiOrder);
            celService.setFromAddress(fromAddress);
            celService.setDriverCars(driverCars);
            celService.setDriversAmount(driversAmount);
            celService.setDuration(duration);

            celServiceDAO.persist(celService);
        } finally {
            if(celServiceDAO != null) {
                celServiceDAO.close();
            }
        }

    }

    public void setSessionContext(SessionContext sessionContext) throws EJBException {
    }

    public void ejbRemove() throws EJBException {
    }

    public void ejbActivate() throws EJBException {
    }

    public void ejbPassivate() throws EJBException {
    }
}
