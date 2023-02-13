package com.ditros.mcd.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import com.ditros.mcd.model.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(foreignKeys = {
                @ForeignKey(entity = VehicleType.class,
                        parentColumns = "id",
                        childColumns = "vehicle_type_id"),

                @ForeignKey(entity = VehicleBrand.class,
                        parentColumns = "id",
                        childColumns = "vehicle_brand_id"),

                @ForeignKey(entity = VehicleModel.class,
                        parentColumns = "id",
                        childColumns = "vehicle_model_id")
        })

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor

public class Vehicle extends JournalData {


    private String chassis;
    private Long cylinderCapacity;
    private Long fabricationYear;


    @ColumnInfo(name="vehicle_type_id")
    private VehicleType type;


    @ColumnInfo(name="vehicle_brand_id")
    private VehicleBrand brand;


    @ColumnInfo(name="vehicle_model_id")
    private VehicleModel model;


}
