package com.netcracker.entity;

import com.netcracker.entity.helper.DriverCar;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Illia Rudenko
 */
@Entity
@Table(name = "celebration")
public class CelebrationService implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer serviceId;

    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private TaxiOrder taxiOrder;

    @JoinColumn(name = "from_addr_id", referencedColumnName = "addr_id")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Address fromAddress;

    @Column(name = "duration")
    @Min(value = 8)
    @Max(value = 1000)
    private Integer duration;

    @Column(name = "drivers_amount")
    @Min(value = 5)
    @Max(value = 100)
    private Integer driversAmount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "celebration_driver_car",
            joinColumns = @JoinColumn(name = "service_id", referencedColumnName = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_car_id", referencedColumnName = "id"))
    private List<DriverCar> driverCars = new ArrayList<DriverCar>();

    public CelebrationService() {

    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public TaxiOrder getTaxiOrder() {
        return taxiOrder;
    }

    public void setTaxiOrder(TaxiOrder taxiOrder) {
        this.taxiOrder = taxiOrder;
    }

    public Address getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(Address fromAddress) {
        this.fromAddress = fromAddress;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDriversAmount() {
        return driversAmount;
    }

    public void setDriversAmount(Integer driversAmount) {
        this.driversAmount = driversAmount;
    }

    public List<DriverCar> getDriverCars() {
        return driverCars;
    }

    public void setDriverCars(List<DriverCar> driverCars) {
        this.driverCars = driverCars;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(taxiOrder, fromAddress, duration, driversAmount);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CelebrationService)) {
            return false;
        }

        CelebrationService other = (CelebrationService) obj;
        if (!Objects.equals(this.taxiOrder, other.taxiOrder)) {
            return false;
        }
        if (!Objects.equals(this.fromAddress, other.fromAddress)) {
            return false;
        }
        if (!Objects.equals(this.duration, other.duration)) {
            return false;
        }
        return Objects.equals(this.driversAmount, other.driversAmount);
    }
}
