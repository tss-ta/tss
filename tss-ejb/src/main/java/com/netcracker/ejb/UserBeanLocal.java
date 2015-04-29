package com.netcracker.ejb;

import com.netcracker.DTO.UserDTO;
import com.netcracker.entity.helper.Roles;

import java.util.List;
import javax.ejb.EJBLocalObject;

public interface UserBeanLocal extends EJBLocalObject {

    void editRoles(int userId, List<Roles> roles);

    /**
     *
     * @param userId
     * @param groupId
     * @return false if this list contained the specified element
     */
    boolean addToGroup(int userId, int groupId);

    List<UserDTO> getCustomers(int pageNumber, int paginationStep);

    List<UserDTO> getAdministrators(int pageNumber, int paginationStep);

    List<UserDTO> getUsersByRolename(Roles role, int pageNumber, int paginationStep);

    List<UserDTO> getUsers(int pageNumber, int paginationStep);

    /**
     *
     * @param userId
     * @param groupId
     * @return true if this list contained the specified element
     */
    public boolean deleteFromGroup(int userId, int groupId);

}
