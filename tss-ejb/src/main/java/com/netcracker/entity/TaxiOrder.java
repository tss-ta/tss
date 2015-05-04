/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.entity;

import com.netcracker.entity.helper.DriverCar;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Виктор
 * @author maks
 */
@Entity
@Table(name = "taxi_order")
@NamedQueries({
		@NamedQuery(name = "TaxiOrder.findAll", query = "SELECT t FROM TaxiOrder t"),
		@NamedQuery(name = "TaxiOrder.findById", query = "SELECT t FROM TaxiOrder t WHERE t.id = :id"),
		@NamedQuery(name = "TaxiOrder.findByPrice", query = "SELECT t FROM TaxiOrder t WHERE t.price = :price"),
		@NamedQuery(name = "TaxiOrder.findByPayment", query = "SELECT t FROM TaxiOrder t WHERE t.payment = :payment"),
		@NamedQuery(name = "TaxiOrder.findByBookingTime", query = "SELECT t FROM TaxiOrder t WHERE t.bookingTime = :bookingTime"),
		@NamedQuery(name = "TaxiOrder.findByOrderTime", query = "SELECT t FROM TaxiOrder t WHERE t.orderTime = :orderTime"),
		@NamedQuery(name = "TaxiOrder.findByMusicStyle", query = "SELECT t FROM TaxiOrder t WHERE t.musicStyle = :musicStyle"),
		@NamedQuery(name = "TaxiOrder.findByStatus", query = "SELECT t FROM TaxiOrder t WHERE t.status = :status"),
		@NamedQuery(name = "TaxiOrder.findByComment", query = "SELECT t FROM TaxiOrder t WHERE t.comment = :comment"),
		@NamedQuery(name = "TaxiOrder.findByMale", query = "SELECT t FROM TaxiOrder t WHERE t.male = :male"),
		@NamedQuery(name = "TaxiOrder.findBySmoke", query = "SELECT t FROM TaxiOrder t WHERE t.smoke = :smoke"),
		@NamedQuery(name = "TaxiOrder.findByCarCategory", query = "SELECT t FROM TaxiOrder t WHERE t.carCategory = :carCategory"),
		@NamedQuery(name = "TaxiOrder.findByAnimalTransport", query = "SELECT t FROM TaxiOrder t WHERE t.animalTransport = :animalTransport"),
		@NamedQuery(name = "TaxiOrder.findByWifi", query = "SELECT t FROM TaxiOrder t WHERE t.wifi = :wifi"),
		@NamedQuery(name = "TaxiOrder.findByConditioner", query = "SELECT t FROM TaxiOrder t WHERE t.conditioner = :conditioner"),
		@NamedQuery(name = "TaxiOrder.findByServiceOptionId", query = "SELECT t FROM TaxiOrder t WHERE t.serviceOptionId = :serviceOptionId") })
public class TaxiOrder implements Serializable {
    
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Integer id;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "price")
	private Double price;
	@Column(name = "payment")
	private Integer payment;
	@Column(name = "booking_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bookingTime;
	@Column(name = "order_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderTime;
	@Column(name = "music_style")
	private Integer musicStyle;
	@Column(name = "status")
	private Integer status;
	@Size(max = 4000)
	@Column(name = "comment")
	private String comment;
	@Column(name = "male")
	private Boolean male;
	@Column(name = "smoke")
	private Boolean smoke;
	@Column(name = "car_category")
	private Integer carCategory;
	@Column(name = "animal_transport")
	private Boolean animalTransport;
	@Column(name = "wifi")
	private Boolean wifi;
	@Column(name = "conditioner")
	private Boolean conditioner;
	@Column(name = "service_option_id")
	private Integer serviceOptionId;
	@JoinColumn(name = "driver_car_id", referencedColumnName = "id")
	@ManyToOne
	private DriverCar driverCarId;
	@JoinColumn(name = "route_id", referencedColumnName = "route_id")
	@ManyToOne
	private Route routeId;
	@JoinColumn(name = "tariff_id", referencedColumnName = "id")
	@ManyToOne
	private Tariff tariffId;
	@JoinColumn(name = "contacts_id", referencedColumnName = "id")
	@ManyToOne
	private Contacts contactsId;

	public TaxiOrder() {
	}

	public TaxiOrder(Integer id) {
		this.id = id;
	}

    public TaxiOrder(Integer payment, Integer musicStyle, Boolean male, Boolean smoke, Integer carCategory, Boolean animalTransport, Boolean wifi, Boolean conditioner) {
        this.payment = payment;
        this.musicStyle = musicStyle;
        this.male = male;
        this.smoke = smoke;
        this.carCategory = carCategory;
        this.animalTransport = animalTransport;
        this.wifi = wifi;
        this.conditioner = conditioner;
    }

    public TaxiOrder(TaxiOrder order) {
        this.id = order.id;
        this.price = order.price;
        this.payment = order.payment;
        this.bookingTime = order.bookingTime;
        this.orderTime = order.orderTime;
        this.musicStyle = order.musicStyle;
        this.status = order.status;
        this.comment = order.comment;
        this.male = order.male;
        this.smoke = order.smoke;
        this.carCategory = order.carCategory;
        this.animalTransport = order.animalTransport;
        this.wifi = order.wifi;
        this.conditioner = order.conditioner;
        this.serviceOptionId = order.serviceOptionId;
        this.driverCarId = order.driverCarId;
        this.routeId = order.routeId;
        this.tariffId = order.tariffId;
        this.contactsId = order.contactsId;
    }
    
//	public TaxiOrder(TaxiOrder to) { //it's not correct to use virtual methods in constructor (maks)
//		setAnimalTransport(to.animalTransport);
//		setBookingTime(to.bookingTime);
//		setCarCategory(to.carCategory);
//		setComment(to.comment);
//		setConditioner(to.conditioner);
//		setDriverCarId(to.driverCarId);
//		setId(to.id);
//		setMale(to.male);
//		setMusicStyle(to.musicStyle);
//		setOrderTime(to.orderTime);
//		setPayment(to.payment);
//		setPrice(to.price);
//		setRouteId(to.routeId);
//		setServiceOptionId(to.serviceOptionId);
//		setSmoke(to.smoke);
//		setStatus(to.status);
//		setTariffId(to.tariffId);
//		setContactsId(to.contactsId);
//		setWifi(to.wifi);
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public Date getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Date bookingTime) {
		this.bookingTime = bookingTime;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getMusicStyle() {
		return musicStyle;
	}

	public void setMusicStyle(Integer musicStyle) {
		this.musicStyle = musicStyle;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getMale() {
		return male;
	}

	public void setMale(Boolean male) {
		this.male = male;
	}

	public Boolean getSmoke() {
		return smoke;
	}

	public void setSmoke(Boolean smoke) {
		this.smoke = smoke;
	}

	public Integer getCarCategory() {
		return carCategory;
	}

	public void setCarCategory(Integer carCategory) {
		this.carCategory = carCategory;
	}

	public Boolean getAnimalTransport() {
		return animalTransport;
	}

	public void setAnimalTransport(Boolean animalTransport) {
		this.animalTransport = animalTransport;
	}

	public Boolean getWifi() {
		return wifi;
	}

	public void setWifi(Boolean wifi) {
		this.wifi = wifi;
	}

	public Boolean getConditioner() {
		return conditioner;
	}

	public void setConditioner(Boolean conditioner) {
		this.conditioner = conditioner;
	}

	public Integer getServiceOptionId() {
		return serviceOptionId;
	}

	public void setServiceOptionId(Integer serviceOptionId) {
		this.serviceOptionId = serviceOptionId;
	}

	public DriverCar getDriverCarId() {
		return driverCarId;
	}

	public void setDriverCarId(DriverCar driverCarId) {
		this.driverCarId = driverCarId;
	}

	public Route getRouteId() {
		return routeId;
	}

	public void setRouteId(Route routeId) {
		this.routeId = routeId;
	}

	public Tariff getTariffId() {
		return tariffId;
	}

	public void setTariffId(Tariff tariffId) {
		this.tariffId = tariffId;
	}

	public Contacts getContactsId() {
		return contactsId;
	}

	public void setContactsId(Contacts contactsId) {
		this.contactsId = contactsId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TaxiOrder)) {
			return false;
		}
		TaxiOrder other = (TaxiOrder) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.netcracker.entity.TaxiOrder[ id=" + id + " ]";
	}

}
