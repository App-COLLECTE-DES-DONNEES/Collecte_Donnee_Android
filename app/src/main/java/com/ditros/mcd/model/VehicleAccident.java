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
        @ForeignKey(entity = VehicleSpecialFunction.class,
                parentColumns = "id",
                childColumns = "vehicle_special_function_id"),

        @ForeignKey(entity = Vehicle.class,
                parentColumns = "id",
                childColumns = "vehicle_id"),

        @ForeignKey(entity = VehicleAction.class,
                parentColumns = "id",
                childColumns = "vehicle_action"),
        
        @ForeignKey(entity = Accident.class,
                parentColumns = "id",
                childColumns = "accident_id"),
})


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleAccident extends JournalData {

    private Long vehicleAccidentNumber;
    private String plateNumber;
    
    
    @ColumnInfo(name="vehicle_special_function_id")
    private String specialFunction;

    @ColumnInfo(name="vehicle_id")
    private String vehicle;

    @ColumnInfo(name="vehicle_action")
    private String action;

    @ColumnInfo(name="accident_id")
    private String accident;


}
