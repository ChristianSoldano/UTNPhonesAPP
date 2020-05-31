package edu.utn.utnphonesapp.model;

import java.util.List;

public class Province {

    private Integer idProvince;
    private String provinceName;
    private List<City> cities;

    public Province() {
    }

    public Province(Integer idProvince, String provinceName, List<City> cities) {
        this.idProvince = idProvince;
        this.provinceName = provinceName;
        this.cities = cities;
    }

    public Integer getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(Integer idProvince) {
        this.idProvince = idProvince;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
