package com.netcracker.entity;

import com.netcracker.entity.helper.Category;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Illia Rudenko
 */
@Entity
@Table(name = "driver")
@PrimaryKeyJoinColumn(name = "driver_id")
@NamedQueries({
    @NamedQuery(name = "Driver.searchDriverByName", query = "SELECT d FROM Driver d WHERE d.username like :drivername"),
    @NamedQuery(name = "Driver.searchDriverByToken", query = "SELECT d FROM Driver d WHERE d.token = :token"),
})
public class Driver extends User {

    @Enumerated(EnumType.ORDINAL)
    private Category category;

    @Column(name = "available")
    private boolean available;

    @Column(name = "is_male")
    private boolean isMale;

    @Column(name = "smokes")
    private boolean smokes;

    @Column(name = "token")
    private Integer token;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public Driver() {

    }

    public Driver(String email, Integer token) {
        super();
        super.setEmail(email);
        this.token = token;
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

    public Driver(Category category,
                  boolean available,
                  boolean isMale,
                  boolean smokes) {
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

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(category, available, isMale, smokes, car);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Driver)) {
            return false;
        }

        Driver other = (Driver) obj;
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.available, other.available)) {
            return false;
        }
        if (!Objects.equals(this.isMale, other.isMale)) {
            return false;
        }
        if (!Objects.equals(this.smokes, other.smokes)) {
            return false;
        }
        return Objects.equals(this.car, other.car);
    }
}
