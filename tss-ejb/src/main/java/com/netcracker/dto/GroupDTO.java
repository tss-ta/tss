/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.dto;

import com.netcracker.entity.helper.Roles;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maks
 */
public class GroupDTO implements Serializable {
 
    private int id;

    private String name;

    private List<Roles> roles;

    public GroupDTO() {
        roles = new ArrayList<>();
    }

    public GroupDTO(String name) {
        this.name = name;
        roles = new ArrayList<>();

    }

    public GroupDTO(String name, List<Roles> roles) {
        this.name = name;
        if (roles == null) {
            this.roles = new ArrayList<>();
        } else {
            this.roles = roles; //have to encapsulate?
        }
    }

    public GroupDTO(int id, String name, List<Roles> roles) {
        this.id = id;
        this.name = name;
        if (roles == null) {
            this.roles = new ArrayList<>();
        } else {
            this.roles = roles; //have to encapsulate?
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || "".equals(name)){
            throw new IllegalArgumentException("Illigal group name");
        }
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
    public void add(Roles role){
        roles.add(role);
    }

}
