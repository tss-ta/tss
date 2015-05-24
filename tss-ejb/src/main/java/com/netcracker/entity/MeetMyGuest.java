package com.netcracker.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Виктор
 */
@Entity
@Table(name = "meet_my_guest")
@NamedQueries({
    @NamedQuery(name = "MeetMyGuest.findAll", query = "SELECT m FROM MeetMyGuest m"),
    @NamedQuery(name = "MeetMyGuest.findByServiceId", query = "SELECT m FROM MeetMyGuest m WHERE m.serviceId = :serviceId"),
    @NamedQuery(name = "MeetMyGuest.findByGuestName", query = "SELECT m FROM MeetMyGuest m WHERE m.guestName = :guestName")})
public class MeetMyGuest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "service_id")
    private Integer serviceId;

    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "guest_name")
    private String guestName;

    @JoinColumn(name = "service_id", referencedColumnName = "service_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Service service;

    public MeetMyGuest() {
    }

    public MeetMyGuest(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public MeetMyGuest(Integer serviceId, String guestName) {
        this.serviceId = serviceId;
        this.guestName = guestName;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public int hashCode() {
        int result = serviceId != null ? serviceId.hashCode() : 0;
        result = 31 * result + (guestName != null ? guestName.hashCode() : 0);
        result = 31 * result + (service != null ? service.getServiceId().hashCode() : 0);
        return result;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MeetMyGuest)) {
            return false;
        }

        MeetMyGuest other = (MeetMyGuest) obj;
        if (!Objects.equals(this.guestName, other.guestName)) {
            return false;
        }
        return Objects.equals(this.service, other.service);
    }

    @Override
    public String toString() {
        return "com.netcracker.entity.MeetMyGuest[ serviceId=" + serviceId + " ]";
    }
}
