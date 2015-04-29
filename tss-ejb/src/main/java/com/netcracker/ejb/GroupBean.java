package com.netcracker.ejb;

import com.netcracker.DTO.GroupDTO;
import com.netcracker.dao.GroupDAO;
import com.netcracker.dao.RoleDAO;
import com.netcracker.entity.Group;
import com.netcracker.entity.Role;
import com.netcracker.entity.helper.Roles;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.persistence.NoResultException;

public class GroupBean implements SessionBean {

    public void addGroup(String groupName, List<Roles> roles) {
        GroupDAO groupDAO = null;
        RoleDAO roleDAO = null;
        try {
            groupDAO = new GroupDAO();
            roleDAO = new RoleDAO();

            if (isGroupPersist(groupName, groupDAO)) {
                throw new IllegalArgumentException("Group with name " + groupName + " is already exist");
            }
            List<Role> roleList = toRoleList(roles, roleDAO);
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

//            if (group == null) { //isPersist
//                throw new IllegalArgumentException("Group with id " + groupId
//                        + " doesn't exist");
//            }
            group.setName(groupName);
            group.setRoles(toRoleList(roles, roleDAO));
            groupDAO.update(group);
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
//            if (role == null) {
//                throw new IllegalArgumentException("Role with name " + rolename + " doesn't exist");
//            }
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

    private boolean isGroupPersist(int groupId, GroupDAO dao) {
        try {
            if (dao.get(groupId) != null) {
                return true;
            } else {
                return false;
            }
        } catch (IllegalArgumentException e) {
            return false;
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
