package edu.utn.utnphonesapp.model;

public class Rate {

    private Integer idRate;
    private City originCity;
    private City destinationCity;
    private Double costPerMinute;
    private Double pricePerMinute;

    public Rate() {
    }

    public Rate(Integer idRate, City originCity, City destinationCity, Double costPerMinute, Double pricePerMinute) {
        this.idRate = idRate;
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.costPerMinute = costPerMinute;
        this.pricePerMinute = pricePerMinute;
    }

    public Integer getIdRate() {
        return idRate;
    }

    public void setIdRate(Integer idRate) {
        this.idRate = idRate;
    }

    public City getOriginCity() {
        return originCity;
    }

    public void setOriginCity(City originCity) {
        this.originCity = originCity;
    }

    public City getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(City destinationCity) {
        this.destinationCity = destinationCity;
    }

    public Double getCostPerMinute() {
        return costPerMinute;
    }

    public void setCostPerMinute(Double costPerMinute) {
        this.costPerMinute = costPerMinute;
    }

    public Double getPricePerMinute() {
        return pricePerMinute;
    }

    public void setPricePerMinute(Double pricePerMinute) {
        this.pricePerMinute = pricePerMinute;
    }
}
