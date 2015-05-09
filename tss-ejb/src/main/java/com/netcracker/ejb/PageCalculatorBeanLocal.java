package com.netcracker.ejb;

import com.netcracker.entity.helper.Pager;

import javax.ejb.EJBLocalObject;

/**
 * @author Kyrylo Berehovyi
 */
public interface PageCalculatorBeanLocal extends EJBLocalObject {
    Pager createCarPager(Integer pageNumber, Integer pageSize);

    Pager createSearchCarPager(Integer pageNumber, Integer pageSize, String searchWord);

    Pager calculatePages(Integer pageNumber, Integer pageSize, Integer amount);

    <T> Pager createPager (Class <T> entity, Integer pageNumber, Integer pageSize);

}
