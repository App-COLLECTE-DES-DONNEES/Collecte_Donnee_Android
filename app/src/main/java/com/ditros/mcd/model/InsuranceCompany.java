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

@Entity(tableName = "InsuranceCompany",
        foreignKeys = {
                @ForeignKey(entity = Country.class,
                        childColumns = "country_id",
                        parentColumns= "id")
        })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InsuranceCompany extends JournalData {
    private String name;
    private String mail;
    private String phone;

    @ColumnInfo(name="country_id")
    private Country country;
}
