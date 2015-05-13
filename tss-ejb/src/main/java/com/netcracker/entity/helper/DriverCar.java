package com.netcracker.entity.helper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

/**
 * @author Illia Rudenko
 */
@Entity
@Table(name = "driver_car")
@NamedQueries({
    @NamedQuery(name = "DriverCar.findByDriverId", query = "select a from DriverCar a \n"
            + "where a.assignedTime in (\n"
            + "    select max(b.assignedTime) \n"
            + "    from DriverCar b \n"
            + "    where b.driverId= :driver_id"
            + ")")})
public class DriverCar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer drvCarId;

    @Column(name = "driver_id")
    private Integer driverId;

    @Column(name = "car_id")
    private Integer carId;

    @Column(name = "assign_time")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar assignedTime;

    @Column(name = "unassign_time")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar unassignedTime;

    public DriverCar() {
    }

    public DriverCar(Integer driverId, Integer carId, Calendar assignedTime) {
        this.driverId = driverId;
        this.carId = carId;
        this.assignedTime = assignedTime;
    }

    public Integer getDrvCarId() {
        return drvCarId;
    }

    public void setDrvCarId(Integer drvCarId) {
        this.drvCarId = drvCarId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Calendar getAssignedTime() {
        return assignedTime;
    }

    public void setAssignedTime(Calendar assignedTime) {
        this.assignedTime = assignedTime;
    }

    public Calendar getUnassignedTime() {
        return unassignedTime;
    }

    public void setUnassignedTime(Calendar unassignedTime) {
        this.unassignedTime = unassignedTime;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(driverId, carId, assignedTime, unassignedTime);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DriverCar)) {
            return false;
        }

        DriverCar other = (DriverCar) obj;
        if (!Objects.equals(this.driverId, other.driverId)) {
            return false;
        }
        if (!Objects.equals(this.carId, other.carId)) {
            return false;
        }
        if (!Objects.equals(this.assignedTime, other.assignedTime)) {
            return false;
        }
        return Objects.equals(this.unassignedTime, other.unassignedTime);
    }

    @Override
    public String toString() {
        return "DriverCar{" + "drvCarId=" + drvCarId + ", driverId=" + driverId
                + ", carId=" + carId + ", assignedTime=" + assignedTime
                + ", unassignedTime=" + unassignedTime + '}';
    }
}
