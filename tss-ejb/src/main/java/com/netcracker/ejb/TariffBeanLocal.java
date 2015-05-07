package com.netcracker.ejb;

import com.netcracker.entity.Tariff;
import com.netcracker.entity.helper.Pager;

import java.util.List;
import javax.ejb.EJBLocalObject;

/**
 * @author maks
 */
public interface TariffBeanLocal extends EJBLocalObject {

    List<Tariff> getTariffPage(int pageNumber, int paginationStep);
    
    List<Tariff> searchTariffByName(String namePart, int pageNumber, int paginationStep);
    
    void editTariff(int tariffId, float additiveCoef, float multCoef);

    Pager getPager(Integer pageNumber, Integer pageSize);
}
