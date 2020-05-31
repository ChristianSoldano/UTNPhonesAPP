package edu.utn.utnphonesapp.model;

import java.sql.Timestamp;

public class Bill {

    private Integer idBill;
    private Line line;
    private Double totalProductionCost;
    private Double totalPrice;
    private Timestamp issueDate;
    private Timestamp expirationDate;
    private Boolean paid;

    public Bill() {
    }

    public Bill(Integer idBill, Line line, Double totalProductionCost, Double totalPrice, Timestamp issueDate, Timestamp expirationDate, Boolean paid) {
        this.idBill = idBill;
        this.line = line;
        this.totalProductionCost = totalProductionCost;
        this.totalPrice = totalPrice;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.paid = paid;
    }

    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Double getTotalProductionCost() {
        return totalProductionCost;
    }

    public void setTotalProductionCost(Double totalProductionCost) {
        this.totalProductionCost = totalProductionCost;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Timestamp issueDate) {
        this.issueDate = issueDate;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }
}
