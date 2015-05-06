package com.netcracker.ejb;

import com.netcracker.dao.CarDao;
import com.netcracker.dao.GenericDAO;
import com.netcracker.entity.helper.Pager;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;

/**
 * @author Kyrylo Berehovyi
 */

public class PageCalculatorBean implements SessionBean {

    private static final Integer FIRST_PAGE = 1;

    public Pager createCarPager(Integer pageNumber, Integer pageSize) {
        CarDao carDao = null;
        Pager pager = null;
        try {
            carDao = new CarDao();
            pager = calculatePages(pageNumber, pageSize, carDao.count());
        } finally {
            if (carDao != null) {
                carDao.close();
            }
        }
        return pager;
    }

    public Pager createSearchCarPager(Integer pageNumber, Integer pageSize, String searchWord) {
        CarDao carDao = null;
        Pager pager = null;
        try {
            carDao = new CarDao();
            Long amount = carDao.countSearchedByLicPlateResults(searchWord);
            pager = calculatePages(pageNumber, pageSize, amount.intValue());
        } finally {
            if (carDao != null) {
                carDao.close();
            }
        }
        return pager;
    }

    private Pager calculatePages(Integer pageNumber, Integer pageSize, Integer amount) {
        if (pageNumber == null) {
            throw new IllegalArgumentException("Integer 'pageNumber' must not be a null.");
        }
        if (pageSize == null) {
            throw new IllegalArgumentException("Integer 'pageSize' must not be a null.");
        }
        if (amount == null) {
            throw new IllegalArgumentException("Integer 'amount' must not be a null.");
        }

        Pager pager = new Pager();
        pager.setFirstPage(calculateFirstPage(pageNumber));
        pager.setPreviousPage(calculatePreviousPage(pageNumber));
        pager.setNextPage(calculateNextPage(pageNumber, pageSize, amount));
        pager.setLastPage(calculateLastPage(pageNumber, pageSize, amount));
        return pager;
    }

    private Integer calculateLastPage(Integer pageNumber, Integer pageSize, Integer amount) {
        int maxPage = calculateLastPageNumber(pageSize, amount);
        if (pageNumber >= maxPage) {
            return null;
        }
        return maxPage;
    }

    private Integer calculateNextPage(Integer pageNumber, Integer pageSize, Integer amount) {
        int maxPage = calculateLastPageNumber(pageSize, amount);
        if(pageNumber >= maxPage) {
            return null;
        }
        return ++pageNumber;
    }

    private Integer calculatePreviousPage(Integer pageNumber) {
        if (pageNumber <= FIRST_PAGE) {
            return null;
        }
        return --pageNumber;
    }

    private Integer calculateFirstPage(Integer pageNumber) {
        if (pageNumber <= FIRST_PAGE) {
            return null;
        } else {
            return FIRST_PAGE;
        }
    }

    private int calculateLastPageNumber(int pageSize, int amount) {
        return (int) Math.ceil((float) amount / pageSize);
    }
    @Override
    public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException {

    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {

    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {

    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {

    }
}
