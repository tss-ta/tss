/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Виктор
 */
@Entity
@Table(name = "driver_car")
@NamedQueries({
    @NamedQuery(name = "DriverCar.findAll", query = "SELECT d FROM DriverCar d"),
    @NamedQuery(name = "DriverCar.findById", query = "SELECT d FROM DriverCar d WHERE d.id = :id"),
    @NamedQuery(name = "DriverCar.findByAssignedTime", query = "SELECT d FROM DriverCar d WHERE d.assignedTime = :assignedTime"),
    @NamedQuery(name = "DriverCar.findByUnassignedTime", query = "SELECT d FROM DriverCar d WHERE d.unassignedTime = :unassignedTime")})
public class DriverCar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "assigned_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignedTime;
    @Column(name = "unassigned_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date unassignedTime;
    @OneToMany(mappedBy = "driverCarId")
    private Collection<TaxiOrder> taxiOrderCollection;
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    @ManyToOne
    private Car carId;
    @JoinColumn(name = "driver_id", referencedColumnName = "driver_id")
    @ManyToOne
    private Driver driverId;

    public DriverCar() {
    }

    public DriverCar(Car carId, Driver driverId, Date assignedTime) {
        this.carId = carId;
        this.driverId = driverId;
        this.assignedTime = assignedTime;
    }

    public DriverCar(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAssignedTime() {
        return assignedTime;
    }

    public void setAssignedTime(Date assignedTime) {
        this.assignedTime = assignedTime;
    }

    public Date getUnassignedTime() {
        return unassignedTime;
    }

    public void setUnassignedTime(Date unassignedTime) {
        this.unassignedTime = unassignedTime;
    }

    public Collection<TaxiOrder> getTaxiOrderCollection() {
        return taxiOrderCollection;
    }

    public void setTaxiOrderCollection(Collection<TaxiOrder> taxiOrderCollection) {
        this.taxiOrderCollection = taxiOrderCollection;
    }

    public Car getCarId() {
        return carId;
    }

    public void setCarId(Car carId) {
        this.carId = carId;
    }

    public Driver getDriverId() {
        return driverId;
    }

    public void setDriverId(Driver driverId) {
        this.driverId = driverId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DriverCar)) {
            return false;
        }
        DriverCar other = (DriverCar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.netcracker.entity.DriverCar[ id=" + id + " ]";
    }
    
}
