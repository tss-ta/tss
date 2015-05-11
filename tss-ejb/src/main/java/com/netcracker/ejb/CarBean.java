package com.netcracker.ejb;

import com.netcracker.dao.CarDao;
import com.netcracker.dao.exceptions.NoSuchEntity;
import com.netcracker.dto.CarDTO;
import com.netcracker.entity.Car;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.persistence.NoResultException;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kyrylo Berehovyi on 29/04/2015.
 */
public class CarBean implements SessionBean {

    public List<Car> getPageOfCars(int pageNumber, int pageSize) {
        CarDao carDao = new CarDao();
        List<Car> carList = null;
        try {
            carList = carDao.getPage(pageNumber, pageSize);
        } finally {
            if (carDao != null) {
                carDao.close();
            }
        }
        return carList;
    }

    public List<Car> searchByLicPlate(String licPlate) {
        CarDao carDao = new CarDao();
        List<Car> carList = null;
        try {
            carList = carDao.searchByLicPlate(licPlate);
        } catch (NoResultException e) {
            return null;
        } finally {
            carDao.close();
        }
        return carList;
    }

    public List<Car> getPageOfCarsSearchedByLicPlate(int pageNumber, int pageSize, String licPlate) {
        CarDao carDao = new CarDao();
        List<Car> carList = null;
        try {
            carList = carDao.getPageOfCarsSearchedByLicPlate(pageNumber, pageSize, licPlate);
        } catch (NoResultException e) {
            return null;
        } finally {
            carDao.close();
        }
        return carList;
    }


    public boolean insertCar(Car car) {
        CarDao carDao = new CarDao();
        try {
            try {
                carDao.findByLicPlate(car.getLicPlate());
                return false;
            }
            catch (NoResultException e) {
                carDao.persist(car);
            }
        } finally {
            if (carDao != null) {
                carDao.close();
            }
        }
        return true;
    }

    public void updateCar(Car car) {
        CarDao carDao = new CarDao();
        try {
            carDao.update(car);
        } finally {
            if (carDao != null) {
                carDao.close();
            }
        }
    }

    public void deleteById(Integer id) {
        CarDao carDao = new CarDao();
        Car car = new Car();
        car.setId(id);
        try {
            carDao.delete(car);
        } finally {
            if (carDao != null) {
                carDao.close();
            }
        }
    }

    public Car getById(Integer id) {
        CarDao carDao = new CarDao();
        Car car;
        try {
            try {
                car = carDao.get(id);
            }
            catch (NoResultException | NoSuchEntity e) {
                car = null;
            }
        } finally {
            if (carDao != null) {
                carDao.close();
            }
        }
        return car;
    }

    private CarDTO convertEntityToTransferObject(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setAnimalable(car.getAnimalable());
        carDTO.setAvailable(car.getAvailable());
        carDTO.setCategory(car.getCategory());
        carDTO.setConfitioner(car.getConditioner());
        carDTO.setWifi(car.getWifi());
        carDTO.setLicPlate(car.getLicPlate());
        return carDTO;
    }

    private List<CarDTO> convertListOfEntitiesToListOfTransferObjects(List<Car> carList) {
        List<CarDTO> carDTOs = new LinkedList<CarDTO>();
        for (Car car : carList) {
            carDTOs.add(convertEntityToTransferObject(car));
        }
        return carDTOs;
    }

    @Override
    public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException {}

    @Override
    public void ejbRemove() throws EJBException, RemoteException {}

    @Override
    public void ejbActivate() throws EJBException, RemoteException {}

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {}
}
