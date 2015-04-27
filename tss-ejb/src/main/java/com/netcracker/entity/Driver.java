package com.netcracker.entity;

import com.netcracker.entity.driverUtil.Category;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Illia Rudenko
 */

@Entity
@Table(name = "driver")
@PrimaryKeyJoinColumn(name = "driver_id")
public class Driver extends User {

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Category category;

    @Column(name = "available")
    private boolean available;

    @Column(name = "is_male")
    private boolean isMale;

    @Column(name = "smokes")
    private boolean smokes;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "car_id")
    private Car car;

    public Driver() {

    }

    public Driver(String username,
                  String email,
                  String passwordHash,
                  Category category,
                  boolean available,
                  boolean isMale,
                  boolean smokes) {
        super(username, email, passwordHash);
        this.category = category;
        this.available = available;
        this.isMale = isMale;
        this.smokes = smokes;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean isMale) {
        this.isMale = isMale;
    }

    public boolean isSmokes() {
        return smokes;
    }

    public void setSmokes(boolean smokes) {
        this.smokes = smokes;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
