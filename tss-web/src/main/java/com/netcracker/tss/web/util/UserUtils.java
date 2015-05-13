package com.netcracker.tss.web.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.netcracker.dao.UserDAO;
import com.netcracker.entity.User;

public class UserUtils {

    public static User findCurrentUser() {
        UserDetails userDetails;
        try {
            userDetails = (UserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
        } catch (ClassCastException e) {
            return null;
        }
        UserDAO userDao = new UserDAO();
        User user = userDao.getByEmail(userDetails.getUsername());
        userDao.close();
        return user;
    }
}
