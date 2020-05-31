package edu.utn.utnphonesapp.model;

import java.util.List;

import edu.utn.utnphonesapp.model.enums.UserRole;
import edu.utn.utnphonesapp.model.enums.UserStatus;

public class User {

    private Integer idUser;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private Integer dni;
    private City city;
    private UserRole role;
    private List<Line> lines;
    private UserStatus status;

    public User() {
    }

    public User(Integer idUser, String username, String password, String name, String lastname, Integer dni, City city, UserRole role, List<Line> lines, UserStatus status) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.city = city;
        this.role = role;
        this.lines = lines;
        this.status = status;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
