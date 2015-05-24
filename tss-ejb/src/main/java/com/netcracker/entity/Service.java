package com.netcracker.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lis
 */
@Entity
@Table(name = "service")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s"),
    @NamedQuery(name = "Service.findByServiceId", query = "SELECT s FROM Service s WHERE s.serviceId = :serviceId"),
    @NamedQuery(name = "Service.findByOrderId", query = "SELECT s FROM Service s WHERE s.orderId = :orderId"),
    @NamedQuery(name = "Service.findByServiceName", query = "SELECT s FROM Service s WHERE s.serviceName = :serviceName")})
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer serviceId;

    // @NotNull
    // @Size(min = 1, max = 60)
    @Column(name = "service_name")
    private String serviceName;

    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @ManyToOne
    private TaxiOrder orderId;

    @OneToMany
    @JoinTable(
            name = "conveycorp",
            joinColumns = @JoinColumn(name = "service_id", referencedColumnName = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "route_id", referencedColumnName = "route_id"))
    private List<Route> routes = new ArrayList<Route>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "service")
    private MeetMyGuest meetMyGuest;

    public Service() {
    }

    public Service(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Service(Integer serviceId, String serviceName) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public TaxiOrder getOrderId() {
        return orderId;
    }

    public void setOrderId(TaxiOrder orderId) {
        this.orderId = orderId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public MeetMyGuest getMeetMyGuest() {
        return meetMyGuest;
    }

    public void setMeetMyGuest(MeetMyGuest meetMyGuest) {
        this.meetMyGuest = meetMyGuest;
    }

    @Override
    public int hashCode() {
        int result = serviceId != null ? serviceId.hashCode() : 0;
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (routes != null ? routes.hashCode() : 0);
        result = 31 * result + (meetMyGuest != null ? meetMyGuest.hashCode() : 0);
        return result;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Service)) {
            return false;
        }

        Service other = (Service) obj;
        if (!Objects.equals(this.serviceName, other.serviceName)) {
            return false;
        }
        if (!Objects.equals(this.orderId, other.orderId)) {
            return false;
        }
        return Objects.equals(this.meetMyGuest, other.meetMyGuest);
    }

    @Override
    public String toString() {
        return "com.netcracker.entity.Service[ serviceId=" + serviceId + " ]";
    }
}
