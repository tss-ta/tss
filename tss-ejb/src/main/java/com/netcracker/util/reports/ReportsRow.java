/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.util.reports;

import java.util.Objects;

/**
 *
 * @author maks
 */
public class ReportsRow implements Comparable<ReportsRow>{

    private String name;
    
    private int value;

    public ReportsRow() {
    }

    public ReportsRow(String name, int value) {
        this.name = name;
        this.value = value;
    }

    
    
    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public long getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReportsRow other = (ReportsRow) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public int compareTo(ReportsRow o) {
        return (int) (o.value -value);
    }
    
}
