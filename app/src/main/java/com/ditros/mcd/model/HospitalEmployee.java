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

@Entity(tableName = "HospitalEmployee",
        foreignKeys = {
                @ForeignKey(entity = Hospital.class,
                        parentColumns = "id",
                        childColumns = "hospital_id"),
                @ForeignKey(entity = User.class,
                        parentColumns = "id",
                        childColumns = "user_id")
        },
        indices = {@Index(value = {"user_id"},
                unique = true)})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HospitalEmployee extends JournalData {
    private String matricule;

    @ColumnInfo(name="user_id")
    private String user;

    @ColumnInfo(name="hospital_id")
    private String hospital;

}
