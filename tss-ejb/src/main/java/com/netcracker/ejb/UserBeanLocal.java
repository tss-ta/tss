package com.netcracker.ejb;

import com.netcracker.dto.UserDTO;
import com.netcracker.entity.Contacts;
import com.netcracker.entity.User;
import com.netcracker.entity.helper.Pager;
import com.netcracker.entity.helper.PersonalAddress;
import com.netcracker.entity.helper.Roles;

import java.util.List;

import javax.ejb.EJBLocalObject;

/**
 * 
 * @author maks
 */
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

    /**
     *
     * @param emailPart - part of email
     * @param pageNumber - number of page
     * @param paginationStep - rows on one page
     * @return List of UserDTO where email have emailPart
     */
    List<UserDTO> searchUsersByEmail(String emailPart, int pageNumber, int paginationStep);

    /**
     *
     * @param emailPart - part of email
     * @param rolename -name of role
     * @param pageNumber - number of page
     * @param paginationStep - rows on one page
     * @return List of UserDTO where email have emailPart and role with rolename
     */
    List<UserDTO> searchUsersByEmailAndRole(String emailPart, Roles role, int pageNumber, int paginationStep);
    
    List<UserDTO> searchCustomersByEmail(String emailPart, int pageNumber, int paginationStep);
    
    Contacts getContacts(int userId);

    List<PersonalAddress> toPersonalAddress(User user);
    
    void addToPersonalList(User user, String pa);
    
    void removeFromPersonalList(User user, String pa);

    Pager getPager(Integer pageNumber, Integer pageSize, Roles role);

    Pager getPager(Integer pageNumber, Integer pageSize);


}
