/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maks
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tariff.findAll", query = "SELECT t FROM Tariff t"),
    @NamedQuery(name = "Tariff.findAllOrderedByName", query = "SELECT t FROM Tariff t ORDER BY t.tariffName"),
    @NamedQuery(name = "Tariff.searchByName", query = "SELECT t FROM Tariff t WHERE t.tariffName like :tariffName"),
    @NamedQuery(name = "Tariff.findById", query = "SELECT t FROM Tariff t WHERE t.id = :id"),
    @NamedQuery(name = "Tariff.findByTariffName", query = "SELECT t FROM Tariff t WHERE t.tariffName = :tariffName"),
    @NamedQuery(name = "Tariff.findByPlusCoef", query = "SELECT t FROM Tariff t WHERE t.plusCoef = :plusCoef"),
    @NamedQuery(name = "Tariff.findByMultipleCoef", query = "SELECT t FROM Tariff t WHERE t.multipleCoef = :multipleCoef")})
public class Tariff implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Size(max = 40)
    @Column(name = "tariff_name")
    private String tariffName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "plus_coef")
    private Float plusCoef;
    @Column(name = "multiple_coef")
    @Min(value=0)
    @Max(value = 100000)
    private Float multipleCoef;

    public Tariff() {
    }

    public Tariff(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public Float getPlusCoef() {
        return plusCoef;
    }

    public void setPlusCoef(Float plusCoef) {
        this.plusCoef = plusCoef;
    }

    public Float getMultipleCoef() {
        return multipleCoef;
    }

    public void setMultipleCoef(Float multipleCoef) {
        this.multipleCoef = multipleCoef;
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
        if (!(object instanceof Tariff)) {
            return false;
        }
        Tariff other = (Tariff) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.netcracker.entity.Tariff[ id=" + id + " ]";
    }
    
}
