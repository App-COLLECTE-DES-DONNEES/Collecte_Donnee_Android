package com.ditros.mcd.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import com.ditros.mcd.model.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(
        foreignKeys = {
                @ForeignKey(entity = PoliceStation.class,
                        parentColumns = "id",
                        childColumns = "police_station_id")
        },
        indices = {@Index(value = {"user_id"},
                unique = true)})
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PoliceOfficer extends JournalData {
    private String matricule;

    @ColumnInfo(name="police_station_id")
    private String policeStation;

}
