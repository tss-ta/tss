package com.netcracker.ejb;

import com.netcracker.dao.CarDao;
import com.netcracker.dao.DriverCarDAO;
import com.netcracker.dao.DriverDAO;
import com.netcracker.dao.exceptions.NoSuchEntityException;
import com.netcracker.entity.Car;
import com.netcracker.entity.Driver;
import com.netcracker.entity.helper.DriverCar;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.persistence.NoResultException;

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
		Driver driver = null;
		try {
			driver = drDao.get(id);
		} catch (NoSuchEntityException e) {
			e.printStackTrace();
		}
		drDao.close();
		return driver;
	}

    public Driver getDriver(String email) {
        DriverDAO drDao = new DriverDAO();
        Driver driver = null;
        try {
            driver = drDao.findByEmail(email);
        } catch (NoResultException e) {
            //NOP
        } finally {
			drDao.close();
		}

        return driver;
    }

    public Driver getDriverByToken(int token) {
        DriverDAO drDao = new DriverDAO();
        Driver driver = null;
        try {
            driver = drDao.getDriverByToken(token);
        } catch (NoResultException e) {
            e.printStackTrace();//!!!!!!!!!!
        }
        drDao.close();
        return driver;
    }




    public void addDriver(Driver driver) {
		DriverDAO drDao = null;
		try {
			drDao = new DriverDAO();
			drDao.persist(driver);
		} finally {
			if(drDao != null) {
				drDao.close();
			}
		}
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
		} catch (NoSuchEntityException e) {
			e.printStackTrace();
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

	public List<Driver> searchDriversByName(String namePart, int pageNumber,
			int pageSize) {
		DriverDAO drDAO = null;
		List<Driver> searchedDrivers;
		try {
			drDAO = new DriverDAO();
			searchedDrivers = drDAO
					.searchByName(namePart, pageNumber, pageSize);
			return searchedDrivers;
		} finally {
			if (drDAO != null) {
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
			carDao = new CarDao();
			Car foundCar = carDao.get(carId);
			foundDriver.setCar(foundCar);
			drCarDao = new DriverCarDAO();
			drCarDao.persist(new DriverCar(driverId, carId, Calendar
					.getInstance()));
		} catch (NoSuchEntityException e) {
			e.printStackTrace();
		} finally {
			if (drDAO != null) {
				drDAO.close();
			}

			if (carDao != null) {
				carDao.close();
			}

			if (drCarDao != null) {
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
			carDao = new CarDao();
			Car foundCar = carDao.get(carId);
			foundDriver.setCar(null);
			drCarDao = new DriverCarDAO();
			DriverCar drCar = drCarDao.getByDriverId(driverId);
			if (drCar != null) {
				// drCarDao.delete(drCar);
				drCar.setUnassignedTime(Calendar.getInstance());
				drCarDao.persist(drCar);
			}
		} catch (NoSuchEntityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (drDAO != null) {
				drDAO.close();
			}

			if (carDao != null) {
				carDao.close();

			}
			if (drCarDao != null) {
				drCarDao.close();
			}
		}

	}

	public void ejbCreate() throws CreateException {
	}

	public void setSessionContext(SessionContext sessionContext)
			throws EJBException {
	}

	public void ejbRemove() throws EJBException {
	}

	public void ejbActivate() throws EJBException {
	}

	public void ejbPassivate() throws EJBException {
	}
}
