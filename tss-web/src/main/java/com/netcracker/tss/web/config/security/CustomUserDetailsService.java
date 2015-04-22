package com.netcracker.tss.web.config.security;

import com.netcracker.dao.UserDAO;
import com.netcracker.entity.Group;
import com.netcracker.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyrylo Berehovyi on 21/04/2015.
 */

public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDAO userDAO = new UserDAO();
        com.netcracker.entity.User user = userDAO.getByEmail(email);

        if (user == null)
            throw new UsernameNotFoundException("user with email " + email + " not found");

        return buildUserFromEntity(user);
    }

    private User buildUserFromEntity(com.netcracker.entity.User entity) {
        String username = entity.getEmail();
        String password = entity.getPasswordHash();
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        addAllEntityUserAuthorities(authorities, entity);
        return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    private void addAllEntityUserAuthorities(List<GrantedAuthority> authorities, com.netcracker.entity.User entity) {
        for (Role role : entity.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }
        for (Group group : entity.getGroups()) {
            for (Role role : group.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getRolename()));
            }
        }
    }
}
