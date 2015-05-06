/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.dto;

import com.netcracker.entity.helper.Roles;
import java.util.List;

/**
 *
 * @author maks
 */
public class UserDTO {

    private int id;

    private String username;

    private String email;

    private List<String> groups;

    private List<Roles> roles;

    public UserDTO() {
    }

    public UserDTO(int id, String username, String email, List<String> groups, List<Roles> userRoles) {
        this.id = id;
        this.username = username;
        this.email = email.toLowerCase();
        this.groups = groups;
        roles = userRoles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email.toLowerCase();
    }

    public List<String> getGroups() {
        return groups;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

}
