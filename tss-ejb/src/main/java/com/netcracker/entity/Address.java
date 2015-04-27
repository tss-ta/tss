/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Виктор
 */
@Entity
@Table(name = "address")
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
    @NamedQuery(name = "Address.findByAddrId", query = "SELECT a FROM Address a WHERE a.addrId = :addrId"),
    @NamedQuery(name = "Address.findByAltitude", query = "SELECT a FROM Address a WHERE a.altitude = :altitude"),
    @NamedQuery(name = "Address.findByLongtitude", query = "SELECT a FROM Address a WHERE a.longtitude = :longtitude")})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addr_id", updatable = false)
    private Integer addrId;
    @NotNull
    @Column(name = "altitude")
    private float altitude;
    @NotNull
    @Column(name = "longtitude")
    private float longtitude;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromAddrId")
    private Collection<Route> routeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toAddrId")
    private Collection<Route> routeCollection1;

    public Address() {
    }

    public Address(Integer addrId) {
        this.addrId = addrId;
    }

    public Address(float altitude, float longtitude) {
        this.altitude = altitude;
        this.longtitude = longtitude;
    }

    public Integer getAddrId() {
        return addrId;
    }

    public void setAddrId(Integer addrId) {
        this.addrId = addrId;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    public float getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(float longtitude) {
        this.longtitude = longtitude;
    }

    public Collection<Route> getRouteCollection() {
        return routeCollection;
    }

    public void setRouteCollection(Collection<Route> routeCollection) {
        this.routeCollection = routeCollection;
    }

    public Collection<Route> getRouteCollection1() {
        return routeCollection1;
    }

    public void setRouteCollection1(Collection<Route> routeCollection1) {
        this.routeCollection1 = routeCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addrId != null ? addrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.addrId == null && other.addrId != null) || (this.addrId != null && !this.addrId.equals(other.addrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.netcracker.entity.Address[ addrId=" + addrId + " ]";
    }

}
