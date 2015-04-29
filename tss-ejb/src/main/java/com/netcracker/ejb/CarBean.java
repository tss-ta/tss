package com.netcracker.ejb;

import com.netcracker.DTO.CarDTO;
import com.netcracker.dao.CarDao;
import com.netcracker.entity.Car;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kyrylo Berehovyi on 29/04/2015.
 */
public class CarBean implements SessionBean {

    private static final int DEFAULT_PAGE_SIZE = 15;

    public List<Car> getPageOfCars(int pageNumber) {
        CarDao carDao = new CarDao();
        List<Car> carList = null;
        try {
            carList = carDao.getPage(pageNumber, DEFAULT_PAGE_SIZE);
        } finally {
            if (carDao != null) {
                carDao.close();
            }
        }
        return carList;
    }

    public void insertCar(Car car) {
        CarDao carDao = new CarDao();
        try {
            carDao.persist(car);
        } finally {
            if (carDao != null) {
                carDao.close();
            }
        }
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
