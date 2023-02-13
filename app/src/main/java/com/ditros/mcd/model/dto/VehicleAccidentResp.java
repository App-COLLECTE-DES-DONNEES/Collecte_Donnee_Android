package com.ditros.mcd.model.dto;

import com.ditros.mcd.model.Vehicle;
import com.ditros.mcd.model.VehicleAction;
import com.ditros.mcd.model.VehicleSpecialFunction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleAccidentResp {
    private Long id;
    private Long vehicleAccidentNumber;
    private String plateNumber;

    private VehicleSpecialFunction specialFunction;

    private VehicleResp vehicle;

    private VehicleAction action;

    private String insuranceName;

    private boolean isInsuranceValid;
}
