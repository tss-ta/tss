/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import com.netcracker.DTO.CustomerDTO;
import com.netcracker.DTO.GroupDTO;
import com.netcracker.dao.GroupDAO;
import com.netcracker.dao.RoleDAO;
import com.netcracker.dao.UserDAO;
import com.netcracker.entity.Group;
import com.netcracker.entity.Role;
import com.netcracker.entity.User;
import com.netcracker.entity.helper.Roles;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.persistence.NoResultException;

/**
 *
 * @author Виктор
 */
public class CustomerBean implements SessionBean {

//
    public void editRoles(CustomerDTO customerDTO) {
        UserDAO userDAO = null;
        RoleDAO roleDAO = null;
        try {
            String email = customerDTO.getEmail();
            userDAO = new UserDAO();
            roleDAO = new RoleDAO();
            User user = userDAO.getByEmail(email);

            user.setRoles(toRoleList(customerDTO.getRoles(), roleDAO));
            userDAO.update(user);
        } finally {
            if (roleDAO != null) {
                roleDAO.close();
            }
            if (userDAO != null) {
                userDAO.close();
            }
        }
    }

    public void editGroups(CustomerDTO customerDTO) {
        GroupDAO groupDAO = null;
        UserDAO userDAO = null;
        try {
            String email = customerDTO.getEmail();
            groupDAO = new GroupDAO();
            userDAO = new UserDAO();
            User user = userDAO.getByEmail(email);

//            if (group == null) { //isPersist
//                throw new IllegalArgumentException("Group with id " + groupId
//                        + " doesn't exist");
//            }
            user.setGroups(toGroupList(customerDTO.getGroups(), groupDAO));
            userDAO.update(user);
        } finally {
            if (userDAO != null) {
                userDAO.close();
            }
            if (groupDAO != null) {
                groupDAO.close();
            }
        }
    }

    private List<Group> toGroupList(List<String> groupNames, GroupDAO dao) {
        List<Group> groups = new ArrayList<>();
        Iterator<String> groupNamesIterator = groupNames.iterator();
        while (groupNamesIterator.hasNext()) {
            Group group = dao.findByName(groupNamesIterator.next());
            groups.add(group);
        }
        return groups;
    }

    private List<Role> toRoleList(List<Roles> roles, RoleDAO roleDAO) {
        List<Role> roleList = new ArrayList<>();
        Iterator<Roles> rolesIterator = roles.iterator();
        while (rolesIterator.hasNext()) {
            String rolename = rolesIterator.next().toString();
            Role role = roleDAO.findByRolename(rolename);
//            if (role == null) {
//                throw new IllegalArgumentException("Role with name " + rolename + " doesn't exist");
//            }
            roleList.add(role);
        }
        return roleList;
    }
//
//    private boolean isGroupPersist(String groupName, GroupDAO dao) {
//        try {
//            if (dao.findByName(groupName) != null) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (NoResultException e) {
//            return false;
//        } catch (IllegalArgumentException e) {
//            return false;
//        }
//    }
//
//    private boolean isGroupPersist(int groupId, GroupDAO dao) {
//        try {
//            if (dao.get(groupId) != null) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (IllegalArgumentException e) {
//            return false;
//        }
//    }

    public List<CustomerDTO> getCustomers(int pageNumber, int paginationStep) {
        UserDAO dao = null;
        try {
            dao = new UserDAO();
            List<CustomerDTO> customers = new ArrayList<>();
            List<User> customerUsers = dao.getByRolename(Roles.CUSTOMER.toString(), pageNumber, paginationStep);
            Iterator<User> customersIterator = customerUsers.iterator();
            while (customersIterator.hasNext()) {
                User customer = customersIterator.next();
                customers.add(new CustomerDTO(customer.getUsername(), customer.getEmail(),
                        toGroupNameList(customer.getGroups()), toEnumRolesList(customer.getRoles())));
            }
            return customers;
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }

    public List<String> toGroupNameList(List<Group> groups) {
        List<String> names = new ArrayList<>();
        Iterator<Group> groupIterator = groups.iterator();
        while (groupIterator.hasNext()) {
            names.add(groupIterator.next().getName());
        }
        return names;
    }

    public List<Roles> toEnumRolesList(List<Role> roleList) {
        List<Roles> rolesList = new ArrayList<Roles>(); //enum
        Iterator<Role> roleIterator = roleList.iterator();
        while (roleIterator.hasNext()) {
            String roleName = roleIterator.next().getRolename();
            rolesList.add(Roles.valueOf(roleName));
        }
        return rolesList;
    }

    @Override
    public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {

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
