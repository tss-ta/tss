/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.DTO;

import com.netcracker.entity.driverUtil.Roles;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author maks
 */
public class GroupDTO implements Serializable {

    private int id;

    private String name;

    private List<Roles> roles;

    public GroupDTO(String name, List<Roles> roles) {
        this.name = name;
        this.roles = roles; //have to encapsulate?
    }

    public GroupDTO(int id, String name, List<Roles> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles; //have to encapsulate?
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public List<Roles> getRoles() {
        return roles;
    }



}
