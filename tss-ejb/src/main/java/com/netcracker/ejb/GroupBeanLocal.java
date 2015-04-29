
package com.netcracker.ejb;


import com.netcracker.DTO.GroupDTO;
import com.netcracker.entity.helper.Roles;

import java.util.List;
import javax.ejb.EJBLocalObject;


public interface GroupBeanLocal extends EJBLocalObject {
    
    void addGroup(String groupName, List<Roles> roles);
    
    void editGroup(int groupId, String groupName, List<Roles> roles);
    
    List<GroupDTO> getGroupPage(int pageNumber, int paginationStep);
    
    void deleteGroup (int id);
}
