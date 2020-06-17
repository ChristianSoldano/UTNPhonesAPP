package edu.utn.utnphonesapp.model;

import edu.utn.utnphonesapp.model.enums.LineStatus;
import edu.utn.utnphonesapp.model.enums.LineType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Line {

    private Integer idLine;
    private User user;
    private City city;
    private String phoneNumber;
    private LineType lineType;
    private LineStatus lineStatus;
}
