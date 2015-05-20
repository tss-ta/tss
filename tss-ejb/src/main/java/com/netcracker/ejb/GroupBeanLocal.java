
package com.netcracker.ejb;


import com.netcracker.dto.GroupDTO;
import com.netcracker.entity.Group;
import com.netcracker.entity.helper.Pager;
import com.netcracker.entity.helper.Roles;

import java.util.List;
import javax.ejb.EJBLocalObject;

/**
 * @author maks
 */
public interface GroupBeanLocal extends EJBLocalObject {
    
    void addGroup(String groupName, List<Roles> roles);
    
    void editGroup(int groupId, String groupName, List<Roles> roles);
    
    List<GroupDTO> getGroupPage(int pageNumber, int paginationStep);
    
    void deleteGroup (int id);
    
    List<GroupDTO> searchGroupByName(String namePart, int pageNumber, int paginationStep);

    Pager getPager(Integer pageNumber, Integer pageSize);

    Pager getPager(Integer pageNumber, Integer pageSize, String namePart);

    boolean isGroupContainsRole(Group group, Roles role);

    Group getGroup(int id);
}
