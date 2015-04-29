/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Виктор
 */
@Entity
@Table(name = "route")
@NamedQueries({
    @NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r"),
    @NamedQuery(name = "Route.findByRouteId", query = "SELECT r FROM Route r WHERE r.routeId = :routeId"),
    @NamedQuery(name = "Route.findByPathContent", query = "SELECT r FROM Route r WHERE r.pathContent = :pathContent")})
public class Route implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id", updatable = false)
    private Integer routeId;
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "path_content")
    private String pathContent;
    @JoinColumn(name = "from_addr_id", referencedColumnName = "addr_id")
    @ManyToOne(optional = false)
    private Address fromAddrId;
    @JoinColumn(name = "to_addr_id", referencedColumnName = "addr_id")
    @ManyToOne(optional = false)
    private Address toAddrId;
    @OneToMany(mappedBy = "routeId")
    private Collection<TaxiOrder> taxiOrderCollection;

    public Route() {
    }

    public Route(Integer routeId) {
        this.routeId = routeId;
    }

    public Route(String pathContent) {
        this.pathContent = pathContent;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getPathContent() {
        return pathContent;
    }

    public void setPathContent(String pathContent) {
        this.pathContent = pathContent;
    }

    public Address getFromAddrId() {
        return fromAddrId;
    }

    public void setFromAddrId(Address fromAddrId) {
        this.fromAddrId = fromAddrId;
    }

    public Address getToAddrId() {
        return toAddrId;
    }

    public void setToAddrId(Address toAddrId) {
        this.toAddrId = toAddrId;
    }

    public Collection<TaxiOrder> getTaxiOrderCollection() {
        return taxiOrderCollection;
    }

    public void setTaxiOrderCollection(Collection<TaxiOrder> taxiOrderCollection) {
        this.taxiOrderCollection = taxiOrderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (routeId != null ? routeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Route)) {
            return false;
        }
        Route other = (Route) object;
        if ((this.routeId == null && other.routeId != null) || (this.routeId != null && !this.routeId.equals(other.routeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.netcracker.entity.Route[ routeId=" + routeId + " ]";
    }
    
}
