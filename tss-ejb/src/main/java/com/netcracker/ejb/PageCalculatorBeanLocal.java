package com.netcracker.ejb;

import com.netcracker.entity.helper.Pager;

import javax.ejb.EJBLocalObject;

/**
 * @author Kyrylo Berehovyi
 */
public interface PageCalculatorBeanLocal extends EJBLocalObject {
    Pager createCarPager(Integer pageNumber, Integer pageSize);
}
