package com.netcracker.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

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

    public Address(double[] mass) {
        this((float) mass[1], (float) mass[0]);
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
    public final int hashCode() {
        return Objects.hash(Float.floatToIntBits(altitude), Float.floatToIntBits(longtitude));
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Address)) {
            return false;
        }

        Address other = (Address) obj;
        if (!Objects.equals(
                Float.floatToIntBits(this.altitude),
                Float.floatToIntBits(other.altitude))) {
            return false;
        }
        return Objects.equals(
                Float.floatToIntBits(this.longtitude),
                Float.floatToIntBits(other.longtitude));
    }

    @Override
    public String toString() {
        return "com.netcracker.entity.Address[ addrId=" + addrId + " ]";
    }
}
