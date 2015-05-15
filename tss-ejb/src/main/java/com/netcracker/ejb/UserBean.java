package com.netcracker.ejb;

import com.netcracker.dao.ContactsDAO;
import com.netcracker.dto.UserDTO;
import com.netcracker.dao.RoleDAO;
import com.netcracker.dao.UserDAO;
import com.netcracker.dao.exceptions.NoSuchEntityException;
import com.netcracker.entity.Contacts;
import com.netcracker.entity.Address;
import com.netcracker.entity.Group;
import com.netcracker.entity.Role;
import com.netcracker.entity.User;
import com.netcracker.entity.helper.Pager;
import com.netcracker.entity.helper.PersonalAddress;
import com.netcracker.entity.helper.Roles;
import com.netcracker.util.BeansLocator;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.persistence.NoResultException;

/**
 *
 * @author maks
 */
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
            if (roles.contains(Roles.BANNED)) {
                roles.clear();
                roles.add(Roles.BANNED);
                user.setRoles(toRoleList(roles, roleDAO));
                userDAO.update(user);
                notifyAboutBan(user.getEmail());//!!!!!!!!!!
//                notifyAboutBan("maksbrunarskiy@gmail.com");//!!!!!!!!!!

            }
//            else if (roles.contains(Roles.DRIVER)) {
//
//            }
            else {
                    user.setRoles(toRoleList(roles, roleDAO));
                    userDAO.update(user);

            }
        } catch (NoSuchEntityException e) {
			throw new IllegalArgumentException("User with id = " + userId + " does not exist");
//			throw new InvalidEntityException("User with id = " + userId + " does not exist");
		} finally {
            if (roleDAO != null) {
                roleDAO.close();
            }
            if (userDAO != null) {
                userDAO.close();
            }
        }
    }

    private void notifyAboutBan(String email) {
        MailerBeanLocal mailerBean = BeansLocator.getInstance().getBean(MailerBeanLocal.class);
        mailerBean.sendEmail(email, "Taxi Service System notify", "Sorry, but yours account in Taxi Service System was banned!");
    }

    /**
     *
     * @param userId
     * @param groupId
     * @return false if this list was already contained the specified element
     */
    public boolean addToGroup(int userId, int groupId) {
        if (groupId < 0 || userId < 0) {
            throw new IllegalArgumentException("Id can't be less than zero");
        }

        GroupBeanLocal groupBean = BeansLocator.getInstance().getBean(GroupBeanLocal.class);
        UserDAO userDAO = null;
        try {
            userDAO = new UserDAO();
            User user = userDAO.get(userId);
            Group group = groupBean.getGroup(groupId);
            if (user.getGroups().contains(group)) {
                return false;
            } else {
                user.addGroup(group);
                userDAO.update(user);
                if (groupBean.isGroupContainsRole(group, Roles.BANNED)) {
                    notifyAboutBan(user.getEmail());
                }
                return true;
            }
        } catch (NoSuchEntityException e) {
            throw new IllegalArgumentException("Can't find user with id = " + userId);
             //       + " or group with id " + groupId
		} finally {
            if (userDAO != null) {
                userDAO.close();
            }
        }
    }


    public Pager getPager(Integer pageNumber, Integer pageSize) {
        PageCalculatorBeanLocal pageCalculator = BeansLocator.getInstance().getBean(PageCalculatorBeanLocal.class);
        return pageCalculator.createPager(User.class, pageNumber, pageSize);
    }

    public Pager getPager(Integer pageNumber, Integer pageSize, Roles role) {
        PageCalculatorBeanLocal pageCalculator = BeansLocator.getInstance().getBean(PageCalculatorBeanLocal.class);
        UserDAO userDAO = null;
        Pager pager = null;
        try {
            userDAO = new UserDAO();
            int amount = userDAO.countByUserRoleName(role.toString());
            pager = pageCalculator.calculatePages(pageNumber, pageSize, amount);
        } finally {
            if (userDAO != null) {
                userDAO.close();
            }
        }
        return pager;
    }

    public Pager getPager(Integer pageNumber, Integer pageSize, Roles role, String emailPart) {
        PageCalculatorBeanLocal pageCalculator = BeansLocator.getInstance().getBean(PageCalculatorBeanLocal.class);
        UserDAO userDAO = null;
        Pager pager = null;
        try {
            userDAO = new UserDAO();
            int amount = userDAO.countByEmailAndRolename(emailPart, role.toString());
            pager = pageCalculator.calculatePages(pageNumber, pageSize, amount);
        } finally {
            if (userDAO != null) {
                userDAO.close();
            }
        }
        return pager;
    }

    public Pager getPager(Integer pageNumber, Integer pageSize, String emailPart) {
        PageCalculatorBeanLocal pageCalculator = BeansLocator.getInstance().getBean(PageCalculatorBeanLocal.class);
        UserDAO userDAO = null;
        Pager pager = null;
        try {
            userDAO = new UserDAO();
            int amount = userDAO.countByEmail(emailPart);
            pager = pageCalculator.calculatePages(pageNumber, pageSize, amount);
        } finally {
            if (userDAO != null) {
                userDAO.close();
            }
        }
        return pager;
    }

    /**
     *
     * @param userId - user id
     * @param groupId - group id
     * @return true if this user contained the group
     */
    public boolean deleteFromGroup(int userId, int groupId) {
        if (groupId < 0 || userId < 0) {
            throw new IllegalArgumentException("Id can't be less than zero");
        }

        GroupBeanLocal groupBean = BeansLocator.getInstance().getBean(GroupBeanLocal.class);
        UserDAO userDAO = null;
        try {
            userDAO = new UserDAO();
            User user = userDAO.get(userId);
            Group group = groupBean.getGroup(groupId);
            List<Group> userGroups = user.getGroups();
            if (userGroups.remove(group)) {
                userDAO.update(user);
                return true;
            } else {
                return false;
            }
        } catch (NoSuchEntityException e) {
            throw new IllegalArgumentException("Can't find user with id = " + userId);
//                     + " or group with id " + groupId);
		} finally {
            if (userDAO != null) {
                userDAO.close();
            }
        }
    }

    public Contacts getContacts(int userId) {
        ContactsDAO contactsDAO = null;
        UserDAO userDAO = null;
        Contacts contacts = null;
        try {
            userDAO = new UserDAO();
            User userFromDB = null;
            try {
                userFromDB = userDAO.get(userId);
            } catch (NoResultException | NoSuchEntityException nre) {
                throw new IllegalArgumentException("User with id = " + userId + " is not exist", nre);
            }
            try {
                contactsDAO = new ContactsDAO();
                contacts = contactsDAO.getByEmail(userFromDB.getEmail());
            } catch (NoResultException nre) {
                throw new IllegalArgumentException("Contacts for user with id = " + userId + " are not exist", nre);
            }
            return contacts;
        } finally {
            if (userDAO != null) {
                userDAO.close();
            }
            if (contactsDAO != null) {
                contactsDAO.close();
            }
        }
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
     * @param emailPart - part of email
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

    public List<UserDTO> searchCustomersByEmail(String emailPart, int pageNumber, int paginationStep) {
        return searchUsersByEmailAndRole(emailPart, Roles.CUSTOMER, pageNumber, paginationStep);
    }

    public List<UserDTO> searchUsersByEmailAndRole(String emailPart, Roles role, int pageNumber, int paginationStep) {
        UserDAO dao = null;
        try {
            dao = new UserDAO();
            return toUserDTOList(dao.searchByEmailAndRolename(emailPart, role.toString(), pageNumber, paginationStep));
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

    public List<PersonalAddress> toPersonalAddress(User user) {
        UserDAO dao = null;
        try {
            dao = new UserDAO();
            List<PersonalAddress> personal_addresses = new ArrayList<>();
            List<Address> addresses = dao.getByEmail(user.getEmail())
                    .getAddresses();
            for (Address addr : addresses) {
                personal_addresses.add(new PersonalAddress(addr));
            }
            return personal_addresses;
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }

    public void addToPersonalList(User user, String pa) {
        UserDAO dao = null;
        try {
            dao = new UserDAO();
            User userDB = dao.getByEmail(user.getEmail());
            if (!("".equals(pa))) {
                Address addr = new Address(new MapBean().geocodeAddress(pa));
                if (!userDB.getAddresses().contains(addr)) {
                    userDB.getAddresses().add(addr);
                }
            }
            dao.update(userDB);
        } catch (JSONException e) {
            e.printStackTrace(); //maybe rethrow?????????
        } catch (IOException e) {
            e.printStackTrace(); //maybe rethrow?????????
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }

    public void removeFromPersonalList(User user, String pa) {
        UserDAO dao = null;
        try {
            dao = new UserDAO();
            User userDB = dao.getByEmail(user.getEmail());
            if (!("".equals(pa))) {
                Address addr = new Address(new MapBean().geocodeAddress(pa));
                userDB.getAddresses().remove(addr);
            }
            dao.update(userDB);
        } catch (JSONException e) {
            e.printStackTrace(); //maybe rethrow?????????
        } catch (IOException e) {
            e.printStackTrace(); //maybe rethrow?????????
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
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
