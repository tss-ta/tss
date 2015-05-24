package com.netcracker.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
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
import javax.validation.constraints.Min;
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

    @NotNull
    @Column(name = "distance")
    @Min(value = 0)
    private Float distance;

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

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Collection<TaxiOrder> getTaxiOrderCollection() {
        return taxiOrderCollection;
    }

    public void setTaxiOrderCollection(Collection<TaxiOrder> taxiOrderCollection) {
        this.taxiOrderCollection = taxiOrderCollection;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(pathContent, fromAddrId, toAddrId, Float.floatToIntBits(this.distance));
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Route)) {
            return false;
        }

        Route other = (Route) obj;
        if (!Objects.equals(this.pathContent, other.pathContent)) {
            return false;
        }
        if (!Objects.equals(this.fromAddrId, other.fromAddrId)) {
            return false;
        }
        if (!Objects.equals(this.toAddrId, other.toAddrId)) {
            return false;
        }
        return Objects.equals(
                Float.floatToIntBits(this.distance),
                Float.floatToIntBits(other.distance));
    }

    @Override
    public String toString() {
        return "com.netcracker.entity.Route[ routeId=" + routeId + " ]";
    }
}
