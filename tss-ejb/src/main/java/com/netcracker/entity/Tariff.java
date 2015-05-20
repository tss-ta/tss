
package com.netcracker.entity;

import java.io.Serializable;
import java.util.Objects;
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
    @NamedQuery(name = "Tariff.findAll", query = "SELECT t FROM Tariff t"),
    @NamedQuery(name = "Tariff.findAllOrderedByName", query = "SELECT t FROM Tariff t ORDER BY t.tariffName"),
    @NamedQuery(name = "Tariff.searchByNameOrdered", query = "SELECT t FROM Tariff t WHERE t.tariffName like :tariffName ORDER BY t.tariffName "),
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

    @Size(min = 1, max = 40, message = "tariff name is too short or too long")
    @Column(name = "tariff_name")
    private String tariffName;

    @Max(value=(long)1E10)
    @Min(value=(long)-1E10)
    // if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "plus_coef")
    @NotNull
    private Float plusCoef;

    @Column(name = "multiple_coef")
    @NotNull
    @Min(value = 0, message = "multiplier can't be less than zero")
    @Max(value = 100000, message = "multiplier is too big")
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
    public final int hashCode() {
        return Objects.hash(tariffName, Float.floatToIntBits(this.plusCoef),
                Float.floatToIntBits(this.multipleCoef));
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Tariff)) {
            return false;
        }

        Tariff other = (Tariff) obj;
        if (!Objects.equals(this.tariffName, other.tariffName)) {
            return false;
        }
        if (!Objects.equals(
                Float.floatToIntBits(this.plusCoef),
                Float.floatToIntBits(other.plusCoef))) {
            return false;
        }
        return Objects.equals(
                Float.floatToIntBits(this.multipleCoef),
                Float.floatToIntBits(other.multipleCoef));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tariff{");
        sb.append("id=").append(id);
        sb.append(", tariffName='").append(tariffName).append('\'');
        sb.append(", plusCoef=").append(plusCoef);
        sb.append(", multipleCoef=").append(multipleCoef);
        sb.append('}');
        return sb.toString();
    }
    
}
