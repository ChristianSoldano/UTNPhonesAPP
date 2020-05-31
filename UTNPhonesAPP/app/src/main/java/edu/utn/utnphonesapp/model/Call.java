package edu.utn.utnphonesapp.model;

import java.sql.Timestamp;

public class Call {

    private Integer idCall;
    private Line originLine;
    private Line destinationLine;
    private Timestamp callDate;
    private Rate rate;
    private Integer callDuration;
    private Double callCost;
    private Double callPrice;

    public Call() {
    }

    public Call(Integer idCall, Line originLine, Line destinationLine, Timestamp callDate, Rate rate, Integer callDuration, Double callCost, Double callPrice) {
        this.idCall = idCall;
        this.originLine = originLine;
        this.destinationLine = destinationLine;
        this.callDate = callDate;
        this.rate = rate;
        this.callDuration = callDuration;
        this.callCost = callCost;
        this.callPrice = callPrice;
    }

    public Integer getIdCall() {
        return idCall;
    }

    public void setIdCall(Integer idCall) {
        this.idCall = idCall;
    }

    public Line getOriginLine() {
        return originLine;
    }

    public void setOriginLine(Line originLine) {
        this.originLine = originLine;
    }

    public Line getDestinationLine() {
        return destinationLine;
    }

    public void setDestinationLine(Line destinationLine) {
        this.destinationLine = destinationLine;
    }

    public Timestamp getCallDate() {
        return callDate;
    }

    public void setCallDate(Timestamp callDate) {
        this.callDate = callDate;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public Integer getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(Integer callDuration) {
        this.callDuration = callDuration;
    }

    public Double getCallCost() {
        return callCost;
    }

    public void setCallCost(Double callCost) {
        this.callCost = callCost;
    }

    public Double getCallPrice() {
        return callPrice;
    }

    public void setCallPrice(Double callPrice) {
        this.callPrice = callPrice;
    }
}
