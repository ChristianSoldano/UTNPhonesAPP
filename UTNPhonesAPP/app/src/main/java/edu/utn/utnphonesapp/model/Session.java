package edu.utn.utnphonesapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public final class Session {
    private Integer userId;
    private String token;
}
