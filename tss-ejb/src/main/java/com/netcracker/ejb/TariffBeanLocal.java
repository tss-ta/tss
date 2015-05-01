package com.netcracker.ejb;

import com.netcracker.entity.Tariff;
import java.util.List;
import javax.ejb.EJBLocalObject;

public interface TariffBeanLocal extends EJBLocalObject {

    List<Tariff> getTariffPage(int pageNumber, int paginationStep);
    
    List<Tariff> searchTariffByName(String namePart, int pageNumber, int paginationStep);
}
