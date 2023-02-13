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
        @ForeignKey(entity = VehicleBrand.class,
                parentColumns = "id",
                childColumns = "vehicle_brand_id"),
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleModel extends JournalData {
    private Long code;
    private String value;

    @ColumnInfo(name="vehicle_brand_id")
    private String  vehiculeBrand;
}
