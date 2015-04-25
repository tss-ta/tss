/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import com.netcracker.DTO.GroupDTO;
import com.netcracker.dao.GroupDAO;
import com.netcracker.dao.RoleDAO;
import com.netcracker.dao.UserDAO;
import com.netcracker.entity.Group;
import com.netcracker.entity.Role;
import com.netcracker.entity.User;
import com.netcracker.entity.helpEntity.Roles;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 *
 * @author Виктор
 */
public class GroupBean implements SessionBean {

    public void addGroup(GroupDTO groupDTO) {
        GroupDAO groupDAO = null;
        RoleDAO roleDAO = null;
        try {
            String groupName = groupDTO.getName();
            groupDAO = new GroupDAO();

            if (groupDAO.findByName(groupName) != null) {
                throw new IllegalArgumentException("Group with name " + groupName + " is already exist");
            }
            List<Role> roleList = toRoleList(groupDTO.getRoles(), roleDAO);
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

    public void editGroup(GroupDTO groupDTO) {
        GroupDAO groupDAO = null;
        RoleDAO roleDAO = null;
        try {
            int groupId = groupDTO.getId();
            groupDAO = new GroupDAO();
            Group group = groupDAO.get(groupId);

            if (group == null) {
                throw new IllegalArgumentException("Group with id " + groupId 
                        + " doesn't exist");
            }
            group.setName(groupDTO.getName());
            group.setRoles(toRoleList(groupDTO.getRoles(), roleDAO));
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

    private List<Role> toRoleList(List<Roles> roles, RoleDAO roleDAO) {
        List<Role> roleList = new ArrayList<>();
        Iterator<Roles> rolesIterator = roles.iterator();
        while (rolesIterator.hasNext()) {
            String rolename = rolesIterator.next().getCatStr();
            roleDAO = new RoleDAO();
            Role role = roleDAO.findByRolename(rolename);
            if (role == null) {
                throw new IllegalArgumentException("Role with name " + rolename + " doesn't exist");
            }
            roleList.add(role);
        }
        return roleList;
    }

    private boolean isPersist(String groupName, GroupDAO dao) {
        if (dao.findByName(groupName) != null) {
            return true;
        }
        return false;
    }

    public List<GroupDTO> getGroup(int pageNumber, int paginationStep) {
        GroupDAO dao = null;

        try {
            dao = new GroupDAO();
            List<GroupDTO> groupsDTOPage = new ArrayList<GroupDTO>();
            List<Group> groupsPage = dao.findPage(pageNumber, paginationStep);
            Iterator<Group> groupIterator = groupsPage.iterator();
            while (groupIterator.hasNext()) {
                Group group = groupIterator.next();
                String groupName = group.getName();
                List<Role> roleList = group.getRoles();
                List<Roles> rolesList = new ArrayList<Roles>(); //enum
                Iterator<Role> roleIterator = roleList.iterator();
                while (roleIterator.hasNext()) {
                    String roleName = roleIterator.next().getRolename();
                    rolesList.add(Roles.valueOf(roleName));
                }
                groupsDTOPage.add(new GroupDTO(groupName, rolesList));
            }
            return groupsDTOPage;
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
