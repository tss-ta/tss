package com.netcracker.ejb;

import com.netcracker.dto.UserDTO;
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

public class UserBean implements SessionBean {

    public void editRoles(int userId, List<Roles> roles) {
        if (userId < 0) {
            throw new IllegalArgumentException("Id can't be less than zero");
        }
        UserDAO userDAO = null;
        RoleDAO roleDAO = null;
        try {

            userDAO = new UserDAO();
            roleDAO = new RoleDAO();
            User user = userDAO.get(userId);

            user.setRoles(toRoleList(roles, roleDAO));
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

    /**
     * 
     * @param userId
     * @param groupId
     * @return false if this list was alrady contained the specified element
     */
    public boolean addToGroup(int userId, int groupId) {
        if (groupId < 0 || userId < 0) {
            throw new IllegalArgumentException("Id can't be less than zero");
        }

        GroupDAO groupDAO = null;
        UserDAO userDAO = null;
        try {

            groupDAO = new GroupDAO();
            userDAO = new UserDAO();
            User user = userDAO.get(userId);
            Group group = groupDAO.get(groupId);
            if (user.getGroups().contains(group)) {
                return false;
            } else {
                user.addGroup(group);
                userDAO.update(user);
                return true;
            }

        } finally {
            if (userDAO != null) {
                userDAO.close();
            }
            if (groupDAO != null) {
                groupDAO.close();
            }
        }
    }
    /**
     * 
     * @param userId
     * @param groupId
     * @return true if this user contained the group
     */
    public boolean deleteFromGroup (int userId, int groupId) {
        if (groupId < 0 || userId < 0) {
            throw new IllegalArgumentException("Id can't be less than zero");
        }
        GroupDAO groupDAO = null;
        UserDAO userDAO = null;
        try {

            groupDAO = new GroupDAO();
            userDAO = new UserDAO();
            User user = userDAO.get(userId);
            Group group = groupDAO.get(groupId);
            List <Group> userGroups = user.getGroups();
            if (userGroups.remove(group)){
                userDAO.update(user);
                return true;
            } else {
                return false;
            }


        } finally {
            if (userDAO != null) {
                userDAO.close();
            }
            if (groupDAO != null) {
                groupDAO.close();
            }
        }
    }

//    public void editGroups(int userId, List<String> groupNames) {
//        GroupDAO groupDAO = null;
//        UserDAO userDAO = null;
//        try {
//
//            groupDAO = new GroupDAO();
//            userDAO = new UserDAO();
//            User user = userDAO.get(userId);
//
//            user.setGroups(toGroupList(groupNames, groupDAO));
//            userDAO.update(user);
//        } finally {
//            if (userDAO != null) {
//                userDAO.close();
//            }
//            if (groupDAO != null) {
//                groupDAO.close();
//            }
//        }
//    }
//
//    private List<Group> toGroupList(List<String> groupNames, GroupDAO dao) {
//        List<Group> groups = new ArrayList<>();
//        Iterator<String> groupNamesIterator = groupNames.iterator();
//        while (groupNamesIterator.hasNext()) {
//            Group group = dao.findByName(groupNamesIterator.next());
//            groups.add(group);
//        }
//        return groups;
//    }
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

    public List<UserDTO> getCustomers(int pageNumber, int paginationStep) {
        return getUsersByRolename(Roles.CUSTOMER, pageNumber, paginationStep);
    }

    public List<UserDTO> getAdministrators(int pageNumber, int paginationStep) {
        return getUsersByRolename(Roles.ADMIN, pageNumber, paginationStep);
    }

    public List<UserDTO> getUsersByRolename(Roles role, int pageNumber, int paginationStep) {
        UserDAO dao = null;
        try {
            dao = new UserDAO();
            return toUserDTOList(dao.getByRolename(role.toString(), pageNumber, paginationStep));
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }

    public List<UserDTO> getUsers(int pageNumber, int paginationStep) {
        UserDAO dao = null;
        try {
            dao = new UserDAO();
            return toUserDTOList(dao.getPage(pageNumber, paginationStep));
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }
      
    /**
     * 
     * @param emailPart  - part of email
     * @param pageNumber - number of page 
     * @param paginationStep - rows on one page
     * @return List of UserDTO where email have emailPart 
     */
    public List<UserDTO> searchUsersByEmail(String emailPart, int pageNumber, int paginationStep) {
        UserDAO dao = null;
        try {
            dao = new UserDAO();
            return toUserDTOList(dao.searchByEmail(emailPart, pageNumber, paginationStep));
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }
    

    public List<UserDTO> searchUsersByEmailAndRolename(String emailPart, String rolename, int pageNumber, int paginationStep) {
        UserDAO dao = null;
        try {
            dao = new UserDAO();
            return toUserDTOList(dao.searchByEmailAndRolename(emailPart, rolename, pageNumber, paginationStep));
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }

    private List<UserDTO> toUserDTOList(List<User> users) {
        List<UserDTO> customers = new ArrayList<>();
        Iterator<User> customersIterator = users.iterator();
        while (customersIterator.hasNext()) {
            User customer = customersIterator.next();
            customers.add(new UserDTO(customer.getId(), customer.getUsername(), customer.getEmail(),
                    toGroupNameList(customer.getGroups()), toEnumRolesList(customer.getRoles())));
        }
        return customers;
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
