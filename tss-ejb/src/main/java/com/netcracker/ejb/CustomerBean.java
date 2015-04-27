/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Виктор
 */
public class CustomerBean implements SessionBean {


//
//    public void editGroup(GroupDTO groupDTO) {
//        GroupDAO groupDAO = null;
//        RoleDAO roleDAO = null;
//        try {
//            int groupId = groupDTO.getId();
//            groupDAO = new GroupDAO();
//            roleDAO = new RoleDAO();
//            Group group = groupDAO.get(groupId);
//
////            if (group == null) { //isPersist
////                throw new IllegalArgumentException("Group with id " + groupId
////                        + " doesn't exist");
////            }
//            group.setName(groupDTO.getName());
//
//            groupDAO.update(group);
//        } finally {
//            if (roleDAO != null) {
//                roleDAO.close();
//            }
//            if (groupDAO != null) {
//                groupDAO.close();
//            }
//        }
//    }
//
//
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
//
//    public List<GroupDTO> getGroup(int pageNumber, int paginationStep) {
//        GroupDAO dao = null;
//
//        try {
//            dao = new GroupDAO();
//            List<GroupDTO> groupsDTOPage = new ArrayList<GroupDTO>();
//            List<Group> groupsPage = dao.findPage(pageNumber, paginationStep);
//            Iterator<Group> groupIterator = groupsPage.iterator();
//            while (groupIterator.hasNext()) {
//                Group group = groupIterator.next();
//                String groupName = group.getName();
//                List<Role> roleList = group.getRoles();
//                List<Roles> rolesList = new ArrayList<Roles>(); //enum
//                Iterator<Role> roleIterator = roleList.iterator();
//                while (roleIterator.hasNext()) {
//                    String roleName = roleIterator.next().getRolename();
//                    rolesList.add(Roles.valueOf(roleName));
//                }
//                groupsDTOPage.add(new GroupDTO(group.getId(), groupName, rolesList));
//            }
//            return groupsDTOPage;
//        } finally {
//            if (dao != null) {
//                dao.close();
//            }
//        }
//
//    }

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
