package edu.utn.utnphonesapp.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

    private Integer idBill;
    private Line line;
    private Double totalProductionCost;
    private Double totalPrice;
    private Timestamp issueDate;
    private Timestamp expirationDate;
    private Boolean paid;
}
