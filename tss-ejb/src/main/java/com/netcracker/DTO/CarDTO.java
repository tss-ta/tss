package com.netcracker.DTO;

/**
 * Created by Kyrylo Berehovyi on 29/04/2015.
 */

public class CarDTO {
    private Integer id;
    private boolean available;
    private boolean animalable;
    private boolean wifi;
    private boolean confitioner;
    private String licPlate;
    private int category;

    public CarDTO() {}

    public CarDTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAnimalable() {
        return animalable;
    }

    public void setAnimalable(boolean animalable) {
        this.animalable = animalable;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isConfitioner() {
        return confitioner;
    }

    public void setConfitioner(boolean confitioner) {
        this.confitioner = confitioner;
    }

    public String getLicPlate() {
        return licPlate;
    }

    public void setLicPlate(String licPlate) {
        this.licPlate = licPlate;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarDTO carDTO = (CarDTO) o;

        if (animalable != carDTO.animalable) return false;
        if (available != carDTO.available) return false;
        if (category != carDTO.category) return false;
        if (confitioner != carDTO.confitioner) return false;
        if (wifi != carDTO.wifi) return false;
        if (id != null ? !id.equals(carDTO.id) : carDTO.id != null) return false;
        if (licPlate != null ? !licPlate.equals(carDTO.licPlate) : carDTO.licPlate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (available ? 1 : 0);
        result = 31 * result + (animalable ? 1 : 0);
        result = 31 * result + (wifi ? 1 : 0);
        result = 31 * result + (confitioner ? 1 : 0);
        result = 31 * result + (licPlate != null ? licPlate.hashCode() : 0);
        result = 31 * result + category;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CarDTO{");
        sb.append("id=").append(id);
        sb.append(", available=").append(available);
        sb.append(", animalable=").append(animalable);
        sb.append(", wifi=").append(wifi);
        sb.append(", confitioner=").append(confitioner);
        sb.append(", licPlate='").append(licPlate).append('\'');
        sb.append(", category=").append(category);
        sb.append('}');
        return sb.toString();
    }
}
