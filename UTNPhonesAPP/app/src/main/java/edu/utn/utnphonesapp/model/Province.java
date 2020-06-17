package edu.utn.utnphonesapp.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Province {

    private Integer idProvince;
    private String provinceName;
    private List<City> cities;
}
