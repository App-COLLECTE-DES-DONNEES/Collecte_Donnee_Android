package com.ditros.mcd.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import com.ditros.mcd.model.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(tableName = "Department",
        foreignKeys = {
                @ForeignKey(entity = City.class,
                        parentColumns = "id",
                        childColumns = "region_id")
        })
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Department extends JournalData {

    private String code;
    private String name;

    @ColumnInfo(name="region_id")
    private String region;

}
