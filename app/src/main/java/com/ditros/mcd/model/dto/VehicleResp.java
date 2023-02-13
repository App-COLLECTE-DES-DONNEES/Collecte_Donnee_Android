package com.ditros.mcd.model.dto;

import com.ditros.mcd.model.VehicleBrand;
import com.ditros.mcd.model.VehicleModel;
import com.ditros.mcd.model.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResp {
    private Long id;
    private String chassis;
    private Long cylinderCapacity;
    private Long fabricationYear;

    private VehicleType type;
    private VehicleBrand brand;
    private VehicleModel model;

}
