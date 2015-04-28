package com.netcracker.entity.helper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Illia Rudenko
 */
@Entity
@Table(name = "driver_car")
@NamedQueries({
        @NamedQuery(name = "DriverCar.findByDriverId", query = "SELECT drvCar FROM DriverCar drvCar WHERE drvCar.driverId = :driver_id")})
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
    private Calendar assignedTime;

    @Column(name = "unassign_time")
    private Calendar unassignedTime;

    public DriverCar() {}

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
}
