package com.netcracker.ejb;

import com.netcracker.dto.GroupDTO;
import com.netcracker.dao.GroupDAO;
import com.netcracker.dao.RoleDAO;
import com.netcracker.dao.exceptions.NoSuchEntityException;
import com.netcracker.entity.Group;
import com.netcracker.entity.Role;
import com.netcracker.entity.helper.Pager;
import com.netcracker.entity.helper.Roles;
import com.netcracker.exceptions.InvalidEntityException;
import com.netcracker.util.BeansLocator;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.persistence.NoResultException;

/**
 * @author maks
 */
public class GroupBean implements SessionBean {

    public Group getGroup(int id) {
        GroupDAO dao = null;

        try {
            dao = new GroupDAO();
            Group group = dao.get(id);
            return group;
        } catch (NoSuchEntityException noSuchEntity) {
            throw new IllegalArgumentException("Can't find group with id = " + id); //or another Exception??
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }

    public void addGroup(String groupName, List<Roles> roles) {
        GroupDAO groupDAO = null;
        RoleDAO roleDAO = null;
        try {
            groupDAO = new GroupDAO();
            roleDAO = new RoleDAO();

            if (isGroupPersist(groupName, groupDAO)) {
                throw new InvalidEntityException("Group with name " + groupName + " is already exist");
            }
            List<Role> roleList = toRoleList(roles, roleDAO);
            Group group  = new Group(groupName, roleList);
            validate(group);
            groupDAO.persist(new Group(groupName, roleList));
        } finally {
            if (roleDAO != null) {
                roleDAO.close();
            }
            if (groupDAO != null) {
                groupDAO.close();
            }
        }
    }

    private void validate (Group group){
        ValidatorBeanLocal validatorBean = BeansLocator.getInstance().getBean(ValidatorBeanLocal.class);
        String message = validatorBean.validate(group);
        if (message != null){
            throw new InvalidEntityException(message);
        }
    }

    public void editGroup(int groupId, String groupName, List<Roles> roles) {
        if (groupId < 0) {
            throw new IllegalArgumentException("Id can't be less than zero");
        }
        GroupDAO groupDAO = null;
        RoleDAO roleDAO = null;
        try {

            groupDAO = new GroupDAO();
            roleDAO = new RoleDAO();
            Group group = groupDAO.get(groupId);
            group.setName(groupName);
            group.setRoles(toRoleList(roles, roleDAO));
            validate(group);
            groupDAO.update(group);
        } catch (NoSuchEntityException e) {
            throw new IllegalArgumentException("Can't edit this group! \n Group with id = " + groupId + " doesn't exist");
//			throw new InvalidEntityException("Can't edit this group! Try again later! \n Group with id = " + groupId + " doesn't exist");
		} finally {
            if (roleDAO != null) {
                roleDAO.close();
            }
            if (groupDAO != null) {
                groupDAO.close();
            }
        }
    }

    public void deleteGroup(int id) {
        GroupDAO groupDAO = null;
        try {
            groupDAO = new GroupDAO();
            groupDAO.delete(new Group(id));
        } finally {
            if (groupDAO != null) {
                groupDAO.close();
            }
        }
    }

    private List<Role> toRoleList(List<Roles> roles, RoleDAO roleDAO) {
        List<Role> roleList = new ArrayList<>();
        Iterator<Roles> rolesIterator = roles.iterator();
        while (rolesIterator.hasNext()) {
            String rolename = rolesIterator.next().toString();
            Role role = roleDAO.findByRolename(rolename);
            roleList.add(role);
        }
        return roleList;
    }

    private boolean isGroupPersist(String groupName, GroupDAO dao) {
        try {
            if (dao.findByName(groupName) != null) {
                return true;
            } else {
                return false;
            }
        } catch (NoResultException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isGroupContainsRole(Group group, Roles role) {
        RoleDAO roleDAO = null;
        try {
            roleDAO = new RoleDAO();
            Role roleEntity = roleDAO.findByRolename(role.toString());
            return group.getRoles().contains(roleEntity);
        } finally {
            if (roleDAO != null) {
                roleDAO.close();
            }
        }
    }

    public List<GroupDTO> getGroupPage(int pageNumber, int paginationStep) {
        GroupDAO dao = null;

        try {
            dao = new GroupDAO();
            List<GroupDTO> groupsDTOPage = new ArrayList<GroupDTO>();
            List<Group> groupsPage = dao.findPage(pageNumber, paginationStep);
            Iterator<Group> groupIterator = groupsPage.iterator();
            while (groupIterator.hasNext()) {
                Group group = groupIterator.next();
                String groupName = group.getName();
                groupsDTOPage.add(new GroupDTO(group.getId(), groupName,
                        toEnumRolesList(group.getRoles())));
            }
            return groupsDTOPage;
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }

    public List<GroupDTO> searchGroupByName(String namePart, int pageNumber, int paginationStep) {
        GroupDAO dao = null;
        try {
            dao = new GroupDAO();
            List<GroupDTO> groupsDTOPage = new ArrayList<GroupDTO>();
            List<Group> groupsPage = dao.searchByName(namePart, pageNumber, paginationStep);
            Iterator<Group> groupIterator = groupsPage.iterator();
            while (groupIterator.hasNext()) {
                Group group = groupIterator.next();
                String groupName = group.getName();
                groupsDTOPage.add(new GroupDTO(group.getId(), groupName,
                        toEnumRolesList(group.getRoles())));
            }
            return groupsDTOPage;
        } catch (NoResultException nre) {
            return new ArrayList<>();
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
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

    public Pager getPager(Integer pageNumber, Integer pageSize) {
        PageCalculatorBeanLocal pageCalculator = BeansLocator.getInstance().getBean(PageCalculatorBeanLocal.class);
        return pageCalculator.createPager(Group.class, pageNumber, pageSize);
    }
    public Pager getPager(Integer pageNumber, Integer pageSize, String namePart) {
        PageCalculatorBeanLocal pageCalculator = BeansLocator.getInstance().getBean(PageCalculatorBeanLocal.class);
        GroupDAO groupDAO = null;
        Pager pager = null;
        try {
            groupDAO = new GroupDAO();
            int amount = groupDAO.countByNamePart(namePart);
            pager = pageCalculator.calculatePages(pageNumber, pageSize, amount);
        } finally {
            if (groupDAO != null) {
                groupDAO.close();
            }
        }
        return pager;
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
