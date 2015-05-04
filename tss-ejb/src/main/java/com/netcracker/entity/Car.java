/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @NamedQuery(name = "Car.findByLicPlate", query = "SELECT c FROM Car c WHERE c.licPlate = :licPlate"),
    @NamedQuery(name = "Car.searchByLicPlate", query = "SELECT c FROM Car c WHERE c.licPlate like :licPlate")})
@Table(name = "car")
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "available")
//    @NotNull WTF????? o_O
    private boolean available;

    @Column(name = "category")
    private int category;

    @Column(name = "animalable")
    private boolean animalable;

    @Column(name = "wifi")
    private boolean wifi;

    @Column(name = "conditioner")
//    @NotNull  seriously?.. boolean?
    private boolean conditioner;

    @Column(name = "lic_plate")
    @Size(max = 11, min = 11, message = "License plate size should be equal to 11 characters.")
    @NotNull(message = "License plate should not be empty.")
    @Pattern(regexp = "[a-zA-z]{3}-[0-9]{3}-[a-zA-z]{3}", message = "Invalid license plate. " +
            "Valid license plate should be like YYY-XXX-YYY.")
    private String licPlate;

    public Car() {}

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
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("id=").append(id);
        sb.append(", available=").append(available);
        sb.append(", category=").append(category);
        sb.append(", animalable=").append(animalable);
        sb.append(", wifi=").append(wifi);
        sb.append(", conditioner=").append(conditioner);
        sb.append(", licPlate='").append(licPlate).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (animalable != car.animalable) return false;
        if (available != car.available) return false;
        if (category != car.category) return false;
        if (conditioner != car.conditioner) return false;
        if (wifi != car.wifi) return false;
        if (id != null ? !id.equals(car.id) : car.id != null) return false;
        if (licPlate != null ? !licPlate.equals(car.licPlate) : car.licPlate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (available ? 1 : 0);
        result = 31 * result + category;
        result = 31 * result + (animalable ? 1 : 0);
        result = 31 * result + (wifi ? 1 : 0);
        result = 31 * result + (conditioner ? 1 : 0);
        result = 31 * result + (licPlate != null ? licPlate.hashCode() : 0);
        return result;
    }
}
