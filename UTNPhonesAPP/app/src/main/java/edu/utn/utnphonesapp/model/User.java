package edu.utn.utnphonesapp.model;

import java.util.List;

import edu.utn.utnphonesapp.model.enums.UserRole;
import edu.utn.utnphonesapp.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Integer idUser;

    private String username;

    private String password;

    private String email;

    private String name;

    private String lastname;

    private Integer dni;

    private City city;

    private String address;

    private UserRole role;

    private UserStatus status;

    private List<Line> lines;

}
