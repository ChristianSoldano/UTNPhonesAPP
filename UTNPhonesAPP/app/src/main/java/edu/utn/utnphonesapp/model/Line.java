package edu.utn.utnphonesapp.model;

import edu.utn.utnphonesapp.model.enums.LineStatus;
import edu.utn.utnphonesapp.model.enums.LineType;

public class Line {

    private Integer idLine;
    private User user;
    private City city;
    private String phoneNumber;
    private LineType lineType;
    private LineStatus lineStatus;

    public Line() {
    }

    public Line(Integer idLine, User user, City city, String phoneNumber, LineType lineType, LineStatus lineStatus) {
        this.idLine = idLine;
        this.user = user;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.lineType = lineType;
        this.lineStatus = lineStatus;
    }

    public Integer getIdLine() {
        return idLine;
    }

    public void setIdLine(Integer idLine) {
        this.idLine = idLine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LineType getLineType() {
        return lineType;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }

    public LineStatus getLineStatus() {
        return lineStatus;
    }

    public void setLineStatus(LineStatus lineStatus) {
        this.lineStatus = lineStatus;
    }
}
