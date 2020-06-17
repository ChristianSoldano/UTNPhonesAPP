package edu.utn.utnphonesapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate {

    private Integer idRate;
    private City originCity;
    private City destinationCity;
    private Double costPerMinute;
    private Double pricePerMinute;

}
