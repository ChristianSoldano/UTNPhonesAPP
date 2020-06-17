package edu.utn.utnphonesapp.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Call {

    private Integer idCall;
    private Line originLine;
    private Line destinationLine;
    private Timestamp callDate;
    private Rate rate;
    private Integer callDuration;
    private Double callCost;
    private Double callPrice;
}
