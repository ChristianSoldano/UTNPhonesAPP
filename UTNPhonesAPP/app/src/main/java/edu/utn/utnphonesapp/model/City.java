package edu.utn.utnphonesapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City {

    private Integer idCity;
    private String cityName;
    private Province province;
    private String prefix;
}
