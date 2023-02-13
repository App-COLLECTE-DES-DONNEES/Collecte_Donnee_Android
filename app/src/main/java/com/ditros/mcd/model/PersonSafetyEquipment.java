package com.ditros.mcd.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import com.ditros.mcd.model.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(tableName = "PersonSafetyEquipment",
        foreignKeys = {
                @ForeignKey(entity = WearingHelmet.class,
                        childColumns = "wearing_hemlet_id",
                        parentColumns = "id"),

                @ForeignKey(entity = OccupantRestraintSystem.class,
                        childColumns = "occupant_restraint_system_id",
                        parentColumns = "id")
        })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonSafetyEquipment extends JournalData {

    @ColumnInfo(name="wearing_hemlet_id")
    private String wearingHelmet;
    @ColumnInfo(name="occupant_restraint_system_id")
    private String occupantRestraintSystem;



}
