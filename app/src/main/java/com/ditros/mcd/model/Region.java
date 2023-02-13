package com.ditros.mcd.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import com.ditros.mcd.model.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(
        foreignKeys = {
                @ForeignKey(entity = Country.class,
                        parentColumns = "id",
                        childColumns = "country_id")
        })
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Region extends JournalData {

    private String code;
    private String name;

    @ColumnInfo(name="country_id")
    private String country;
}
