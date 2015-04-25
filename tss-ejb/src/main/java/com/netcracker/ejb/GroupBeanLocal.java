/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;


import com.netcracker.DTO.GroupDTO;
import com.netcracker.entity.User;
import java.util.List;
import javax.ejb.EJBLocalObject;


/**
 *
 * @author Виктор
 */

public interface GroupBeanLocal extends EJBLocalObject {
    void addGroup(GroupDTO groupDTO);
    void editGroup(GroupDTO groupDTO);
    List<GroupDTO> getGroup(int pageNumber, int paginationStep);
}
