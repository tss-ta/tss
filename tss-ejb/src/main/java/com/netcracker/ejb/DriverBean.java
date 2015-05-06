package com.netcracker.ejb;

import com.netcracker.dao.CarDao;
import com.netcracker.dao.DriverCarDAO;
import com.netcracker.dao.DriverDAO;
import com.netcracker.entity.Car;
import com.netcracker.entity.Driver;
import com.netcracker.entity.helper.DriverCar;
import com.netcracker.util.BeansLocator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.util.Calendar;
import java.util.List;

/**
 * @author Illia Rudenko
 */

public class DriverBean implements SessionBean {

    public DriverBean() {
    }


    public Driver getDriver(Integer id) {
        DriverDAO drDao = new DriverDAO();
        Driver driver = drDao.get(id);
        drDao.close();

        return driver;
    }

    public void addDriver(Driver driver) {
        DriverDAO drDao = new DriverDAO();
        drDao.persist(driver);
        drDao.close();
    }

    public void editDriver(Driver driver) {
        DriverDAO drDao = new DriverDAO();
        drDao.update(driver);
        drDao.close();
    }

    public void deleteDriver(Integer driverId) {
        DriverDAO drDao = new DriverDAO();
        try {
            Driver driver = drDao.get(driverId);
            drDao.delete(driver);
        } finally {
            drDao.close();
        }


    }

    public List<Driver> getDriverPage(int pageNumber, int pageSize) {
        DriverDAO drDao = new DriverDAO();
        List<Driver> driversPage = drDao.getPage(pageNumber, pageSize);
        drDao.close();

        return driversPage;
    }

    public List<Driver> searchDriversByName(String namePart, int pageNumber, int pageSize) {
        DriverDAO drDAO = null;
        List<Driver> searchedDrivers;
        try {
            drDAO = new DriverDAO();
            searchedDrivers = drDAO.searchByName(namePart, pageNumber, pageSize);
            return searchedDrivers;
        } finally {
            if(drDAO != null) {
                drDAO.close();
            }
        }
    }

    public void assignCar(Integer driverId, Integer carId) {

        DriverDAO drDAO = null;
        CarDao carDao = null;
        DriverCarDAO drCarDao = null;

        try {
            drDAO = new DriverDAO();
            Driver foundDriver = drDAO.get(driverId);


            if(foundDriver != null) {
                carDao = new CarDao();
                Car foundCar = carDao.get(carId);

                if(foundCar != null) {
                    foundDriver.setCar(foundCar);
                    drCarDao = new DriverCarDAO();
                    drCarDao.persist(new DriverCar(driverId, carId, Calendar.getInstance()));
                }
            }
        } finally {
            if(drDAO != null) {
                drDAO.close();
            }

            if(carDao != null) {
                carDao.close();
            }

            if(drCarDao != null) {
                drCarDao.close();
            }
        }
    }

    public void unassignCar(Integer driverId, Integer carId) {

        DriverDAO drDAO = null;
        CarDao carDao = null;
        DriverCarDAO drCarDao = null;

        try {
            drDAO = new DriverDAO();
            Driver foundDriver = drDAO.get(driverId);

            if(foundDriver != null) {
                carDao = new CarDao();
                Car foundCar = carDao.get(carId);

                if(foundCar != null) {
                    foundDriver.setCar(null);
                    drCarDao = new DriverCarDAO();
                    DriverCar drCar = drCarDao.getByDriverId(driverId);
                    if(drCar != null) {
//                        drCarDao.delete(drCar);
                        drCar.setUnassignedTime(Calendar.getInstance());
                        drCarDao.persist(drCar);
                    }

                }
            }
        } finally {
            if(drDAO != null) {
                drDAO.close();
            }

            if(carDao != null) {
                carDao.close();

            }if(drCarDao != null) {
                drCarDao.close();
            }
        }

    }


    public void ejbCreate() throws CreateException {
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
