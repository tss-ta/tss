/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;


import com.netcracker.DTO.GroupDTO;

import java.util.List;
import javax.ejb.EJBLocalObject;


/**
 *
 * @author Виктор
 */

public interface CustomerBeanLocal extends EJBLocalObject {
    
    void editCustomer(GroupDTO groupDTO);
    
    List<GroupDTO> getCustomers(int pageNumber, int paginationStep);
}
