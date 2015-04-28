package com.netcracker.ejb;

import com.netcracker.dao.CarDao;
import com.netcracker.dao.DriverCarDAO;
import com.netcracker.dao.DriverDAO;
import com.netcracker.entity.Car;
import com.netcracker.entity.Driver;
import com.netcracker.entity.helper.DriverCar;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Illya on 26.04.2015.
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
        drDao.delete(drDao.get(driverId));
        drDao.close();
    }

    public List<Driver> getDriverPage(int pageNumber, int pageSize) {
        DriverDAO drDao = new DriverDAO();
        List<Driver> driversPage = drDao.getPage(pageNumber, pageSize);
        drDao.close();

        return driversPage;
    }

    public void assignCar(Driver driver, Car car) {
        DriverDAO drDAO = new DriverDAO();
        Driver foundDriver = drDAO.get(driver.getId());
        drDAO.close();

        if(foundDriver != null) {
            CarDao carDao = new CarDao();
            Car foundCar = carDao.get(car.getId());
            carDao.close();

            if(foundCar != null) {
                DriverCarDAO drCarDao = new DriverCarDAO();
                drCarDao.persist(new DriverCar(driver.getId(), car.getId(), Calendar.getInstance()));
                drCarDao.close();
            }
        }
    }

    public void unassignCar(Driver driver, Car car) {
        DriverDAO drDAO = new DriverDAO();
        Driver foundDriver = drDAO.get(driver.getId());
        drDAO.close();

        if(foundDriver != null) {
            CarDao carDao = new CarDao();
            Car foundCar = carDao.get(car.getId());
            carDao.close();

            if(foundCar != null) {
                DriverCarDAO drCarDao = new DriverCarDAO();
                DriverCar drCar = drCarDao.getByDriverId(driver.getId());
                if(drCar != null) {
                    drCarDao.delete(drCar);
                    drCar.setUnassignedTime(Calendar.getInstance());
                    drCarDao.persist(drCar);
                }

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
