/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maks
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c"),
    @NamedQuery(name = "Car.findById", query = "SELECT c FROM Car c WHERE c.id = :id"),
    @NamedQuery(name = "Car.findByAvailable", query = "SELECT c FROM Car c WHERE c.available = :available"),
    @NamedQuery(name = "Car.findByCategory", query = "SELECT c FROM Car c WHERE c.category = :category"),
    @NamedQuery(name = "Car.findByAnimalable", query = "SELECT c FROM Car c WHERE c.animalable = :animalable"),
    @NamedQuery(name = "Car.findByWifi", query = "SELECT c FROM Car c WHERE c.wifi = :wifi"),
    @NamedQuery(name = "Car.findByConditioner", query = "SELECT c FROM Car c WHERE c.conditioner = :conditioner"),
    @NamedQuery(name = "Car.findByLicPlate", query = "SELECT c FROM Car c WHERE c.licPlate = :licPlate")})
@Table(name = "car")
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "available")
//    @Basic(optional = false)
    @NotNull
    private boolean available;

    @Column(name = "category")
//    @Basic(optional = false)
    @NotNull
    private int category;

    @Column(name = "animalable")
//    @Basic(optional = false)
    @NotNull
    private boolean animalable;

    @Column(name = "wifi")
    @Basic(optional = false)
    @NotNull
    private boolean wifi;

    @Column(name = "conditioner")
    @Basic(optional = false)
    @NotNull
    private boolean conditioner;

    @Size(max = 10)
    @Column(name = "lic_plate")
    @NotNull
    private String licPlate;

    public Car() {
    }


//    public Car(boolean available, int category, boolean animalable, boolean wifi, boolean conditioner) {
//        this.available = available;
//        this.category = category;
//        this.animalable = animalable;
//        this.wifi = wifi;
//        this.conditioner = conditioner;
//    }

    public Car(String licPlace, boolean available, int category, boolean animalable, boolean wifi, boolean conditioner) {
        this.available = available;
        this.category = category;
        this.animalable = animalable;
        this.wifi = wifi;
        this.conditioner = conditioner;
        this.licPlate = licPlace;
    }

    public Integer getId() {
        return id;
    }

//    public void setId(Integer id) {
//        this.id = id;
//    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean getAnimalable() {
        return animalable;
    }

    public void setAnimalable(boolean animalable) {
        this.animalable = animalable;
    }

    public boolean getWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean getConditioner() {
        return conditioner;
    }

    public void setConditioner(boolean conditioner) {
        this.conditioner = conditioner;
    }

    public String getLicPlate() {
        return licPlate;
    }

    public void setLicPlate(String licPlate) {
        this.licPlate = licPlate;
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
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.netcracker.entity.Car[ id=" + id + " ]";
    }
    
}
