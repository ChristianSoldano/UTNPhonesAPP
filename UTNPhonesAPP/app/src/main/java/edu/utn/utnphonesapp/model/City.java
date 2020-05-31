package edu.utn.utnphonesapp.model;

public class City {

    private Integer idCity;
    private String cityName;
    private Province province;
    private String prefix;

    public City() {
    }

    public City(Integer idCity, String cityName, Province province, String prefix) {
        this.idCity = idCity;
        this.cityName = cityName;
        this.province = province;
        this.prefix = prefix;
    }

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
